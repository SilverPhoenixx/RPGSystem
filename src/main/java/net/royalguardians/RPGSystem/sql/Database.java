package net.royalguardians.RPGSystem.sql;

import net.royalguardians.RPGSystem.player.RPGPlayer;
import net.royalguardians.RPGSystem.RPGSystem;
import net.royalguardians.RPGSystem.enums.Gender;
import net.royalguardians.RPGSystem.enums.NobleTitle;
import net.royalguardians.RPGSystem.playerclass.PlayerClass;
import net.royalguardians.RPGSystem.playerclass.PlayerClassEnum;
import net.royalguardians.RPGSystem.specie.SpecieEnum;
import net.royalguardians.RPGSystem.jobs.jobsEnums.JobEnum;
import net.royalguardians.RPGSystem.jobs.PlayerJobs;
import net.royalguardians.RPGSystem.player.RPGStats;
import org.bukkit.block.Structure;
import org.bukkit.entity.Player;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.function.Consumer;

public class Database {
    private final String host;
    private final int port;
    private final String database;
    private final String user;
    private final String password;
    private Connection connection;
    private PreparedStatement stmt;

    public Database(String host, int port, String database, String user, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.user = user;
        this.password = password;
        createTables();
    }

    //CREATE
    public synchronized void createTables() {
        try {
            Connection();
          Statement stm = connection.createStatement();
         /*
         Tabellen Erstellung:
         RPGPlayer: Id - Level - EXP - Gender - Specie - Title - Gold - Class - FirstJoin - LastJoin [Unique Id]
         Jobs: Id - Name - EXP - Level
         JobNAME: Id - Object - Count
         RPGStats: Id - PointsHP - CurrentHP - DEF - DMG - MOVSPD - CRT - PointsMana - CurrentMana - Points - Collected
          */

          stm.executeUpdate("CREATE TABLE IF NOT EXISTS RPGPlayer (`Id` int(8),`Level` int(12), `EXP` int(12),`Gender` int(1),`Specie` int(2), `Title` int(2), `Gold` varchar(32), `Class` int(8), `FirstJoin` varchar(25), `LastQuit` varchar(25),  UNIQUE (Id), PRIMARY KEY (Id))");
          stm.executeUpdate("CREATE TABLE IF NOT EXISTS Jobs (`Id` int(8),`Name` varchar(32), `EXP` int(12),`Level` int(8), UNIQUE (`Id`, `Name`))");
          stm.executeUpdate("CREATE TABLE IF NOT EXISTS RPGStats (`Id` int(8), `PointsHP` int(8), `CurrentHP` DOUBLE(12,2), `DEF` int(8), `DMG` int(8), `MOVSPD` int(8), `CRT` int(8), `PointsMana` int(8), `CurrentMana` DOUBLE(12,2), `Points` int(8), `Collected` int(8),  UNIQUE (Id),  PRIMARY KEY (Id))");
          for(JobEnum jobEnum : JobEnum.values()) {
              stm.executeUpdate("CREATE TABLE IF NOT EXISTS Job"+ jobEnum.name() + " (`Id` int(8),`Object` varchar(36), `Count` int(12),  UNIQUE (`Id` , `Object`))");
          }
          stm.close();
          close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    //INSERT
    public synchronized void getPlayer(Player p, Consumer<RPGPlayer> consumer, int id) {
        try {
            Connection();
           // stmt = connection.prepareStatement("SELECT * FROM RPGPlayer WHERE Id = ?");
            stmt = connection.prepareStatement("SELECT * FROM RPGPlayer INNER JOIN RPGStats ON RPGPlayer.Id=RPGStats.Id WHERE RPGPlayer.Id = ?");
            stmt.setInt(1, id);
            ResultSet resultset = stmt.executeQuery();
            if(resultset.next()) {
                RPGStats rpgStats = new RPGStats(resultset.getInt("PointsHP"),
                        resultset.getDouble("CurrentHP"),
                        resultset.getInt("DMG"),
                        resultset.getInt("MOVSPD"),
                        resultset.getInt("DEF"),
                        resultset.getInt("CRT"),
                        resultset.getInt("PointsMana"),
                        resultset.getDouble("CurrentMana"),
                        resultset.getInt("Points"),
                        resultset.getInt("Collected"));

                Gender gender = Gender.getGenderByOrdinal(resultset.getInt("Gender"));
                SpecieEnum specie = SpecieEnum.getSpecieByOrdinal(resultset.getInt("Specie"));
                NobleTitle title = NobleTitle.getNobleTitleByOrdinary(resultset.getInt("Title"));
                PlayerClass playerClass = PlayerClassEnum.getPlayerClass(resultset.getInt("Class"));
                double gold = Double.valueOf(resultset.getString("Gold"));
                RPGPlayer rpgPlayer = new RPGPlayer(p, resultset.getInt("Level"), resultset.getInt("EXP"), title, RPGSystem.species.get(specie), gender, gold, id, null, rpgStats, playerClass);
                stmt.close();
                resultset.close();

                HashMap<JobEnum, PlayerJobs>  playerJobsList = new HashMap<>();
                stmt = connection.prepareStatement("SELECT * FROM Jobs WHERE Id = ?");
                stmt.setInt(1, id);
                resultset = stmt.executeQuery();
                while (resultset.next()) {
                    JobEnum jobEnum = JobEnum.getJobEnumByName(resultset.getString("Name"));
                    if(jobEnum == null) continue;
                    PlayerJobs job = PlayerJobs.getPlayerJob(jobEnum, resultset.getInt("Level"), resultset.getInt("EXP"), null, p);
                    playerJobsList.put(jobEnum, job);
                }
                stmt.close();
                resultset.close();

                for(JobEnum jobEnum : JobEnum.values())  {
                    stmt = connection.prepareStatement("SELECT * FROM Job" + jobEnum.name() +" WHERE Id = ?");
                    stmt.setInt(1, id);
                    resultset = stmt.executeQuery();
                    HashMap<String, Integer> list = new HashMap<>();
                    while (resultset.next()) {
                        list.put(resultset.getString("Object"), resultset.getInt("Count"));
                    }

                    if(playerJobsList.get(jobEnum) == null) {
                        PlayerJobs job = PlayerJobs.getPlayerJob(jobEnum, 0, 0, list, p);
                        playerJobsList.put(jobEnum, job);
                    } else {
                        playerJobsList.get(jobEnum).setLists(list);
                    }
                    resultset.close();
                    stmt.close();
                }
                close();

                RPGSystem.players.put(p, rpgPlayer);
                rpgPlayer.setJobs(playerJobsList);
                consumer.accept(rpgPlayer);
            } else {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy-HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                String format = dtf.format(now);
                stmt = connection.prepareStatement("INSERT INTO RPGPlayer(Id, Level, EXP, Gender, Specie, Title, Gold, Class, FirstJoin, LastQuit) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE Id = ?");
                stmt.setInt(1, id);
                stmt.setInt(2, 0);
                stmt.setInt(3, 0);
                stmt.setInt(4, 0);
                stmt.setInt(5, 0);
                stmt.setInt(6, 0);
                stmt.setString(7, "" + 0);
                stmt.setInt(8, 0);
                stmt.setString(9, format);
                stmt.setString(10, format);
                stmt.setInt(11, id);
                stmt.executeUpdate();
                stmt.close();

                stmt = connection.prepareStatement("INSERT INTO RPGStats(Id, PointsHP, CurrentHP, DEF, DMG, MOVSPD, CRT, PointsMana, CurrentMana, Points, Collected) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE Id = ?");
                stmt.setInt(1, id);
                stmt.setInt(2, 0);
                stmt.setDouble(3, 0);
                stmt.setInt(4, 0);
                stmt.setInt(5, 0);
                stmt.setInt(6, 0);
                stmt.setInt(7, 0);
                stmt.setInt(8, 0);
                stmt.setDouble(9, 0);
                stmt.setInt(10, 0);
                stmt.setInt(11, 0);
                stmt.setInt(12, id);
                stmt.executeUpdate();
                stmt.close();

                HashMap<JobEnum, PlayerJobs> jobs = new HashMap<>();

                for(JobEnum jobEnum : JobEnum.values()) {
                    stmt = connection.prepareStatement("INSERT INTO Jobs(Id, Name, EXP, Level) VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE Id = ?");
                    stmt.setInt(1, id);
                    stmt.setString(2, jobEnum.name());
                    stmt.setInt(3, 0);
                    stmt.setInt(4, 0);
                    stmt.setInt(5, id);
                    stmt.executeUpdate();
                    stmt.close();

                    PlayerJobs job = PlayerJobs.getPlayerJob(jobEnum, 0, 0, null, p);
                    for(String name : job.getList().keySet()) {
                        stmt = connection.prepareStatement("INSERT INTO Job" + jobEnum.name() + "(Id, Object, Count) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE Id = ?");
                        stmt.setInt(1, id);
                        stmt.setString(2, name);
                        stmt.setInt(3, 0);
                        stmt.setInt(4, id);
                        stmt.executeUpdate();
                        stmt.close();
                    }
                    jobs.put(jobEnum, job);
                }

                close();
                Gender gender = Gender.getGenderByOrdinal(0);
                NobleTitle title = NobleTitle.getNobleTitleByOrdinary(0);
                SpecieEnum specie = SpecieEnum.getSpecieByOrdinal(99);
                PlayerClass playerClass = PlayerClassEnum.getPlayerClass(0);
                RPGPlayer rpgPlayer = new RPGPlayer(p,0, 0, title, RPGSystem.species.get(specie), gender, 0, id, jobs, new RPGStats(0,specie.getHealth(),0, 0,0,0, 0, specie.getMana(),0,0), playerClass);
                RPGSystem.players.put(p, rpgPlayer);
                consumer.accept(rpgPlayer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            consumer.accept(null);
        }
    }

    //DELETE
    public synchronized void removePlayer(int id) {
        try {
            Connection();
            // "DELETE FROM RPGPlayer WHERE UUID = ? AND Name = ?")
            stmt = connection.prepareStatement("DELETE FROM RPGPlayer WHERE Id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //UPDATE
    public synchronized void savePlayer(RPGPlayer rpgPlayer) {
        try {
            Connection();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy-HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String format = dtf.format(now);
            stmt = connection.prepareStatement("UPDATE RPGPlayer SET Level = ?, EXP = ?, Gender = ?, Specie = ?, Title = ?, Gold = ?, Class = ?, LastQuit = ? WHERE Id = ?");
            stmt.setInt(1, rpgPlayer.getLevel());
            stmt.setInt(2, rpgPlayer.getExp());
            stmt.setInt(3, rpgPlayer.getGender().getOrdinal());
            stmt.setInt(4, rpgPlayer.getSpecie().getSpecieEnum().getOrdinal());
            stmt.setInt(5, rpgPlayer.getTitle().getOrdinal());
            stmt.setDouble(6, rpgPlayer.getGold());
            stmt.setInt(7, rpgPlayer.getPlayerClass().getPlayerClass().getOrdinal());
            stmt.setString(8, format);
            stmt.setInt(9, rpgPlayer.getId());
            stmt.executeUpdate();
            stmt.close();

            stmt = connection.prepareStatement("UPDATE RPGStats SET PointsHP = ?, CurrentHP = ?, DEF = ?, DMG = ?, MOVSPD = ?, CRT = ?, PointsMana = ?, CurrentMana = ?, Points = ?, Collected = ? WHERE Id = ?");
            stmt.setInt(1, rpgPlayer.getStats().getPointsHP());
            stmt.setDouble(2,  rpgPlayer.getStats().getCurrentHP());
            stmt.setInt(3,  rpgPlayer.getStats().getDefense());
            stmt.setInt(4,  rpgPlayer.getStats().getDamage());
            stmt.setInt(5,  rpgPlayer.getStats().getMovementSpeed());
            stmt.setInt(6,  rpgPlayer.getStats().getCritical());
            stmt.setInt(7,  rpgPlayer.getStats().getPointsMana());
            stmt.setDouble(8,  rpgPlayer.getStats().getCurrentMana());
            stmt.setInt(9,  rpgPlayer.getStats().getPoints());
            stmt.setInt(10,  rpgPlayer.getStats().getCollected());
            stmt.setInt(11, rpgPlayer.getId());
            stmt.executeUpdate();
            stmt.close();

            for(JobEnum jobEnum : JobEnum.values()) {
                stmt = connection.prepareStatement("Update Jobs SET EXP = ?, Level = ? WHERE Id = ? AND Name = ?");
                stmt.setInt(1, rpgPlayer.getJob(jobEnum).getExp());
                stmt.setInt(2, rpgPlayer.getJob(jobEnum).getLevel());
                stmt.setInt(3, rpgPlayer.getId());
                stmt.setString(4, jobEnum.name());
                stmt.executeUpdate();
                stmt.close();
            }

            for(JobEnum jobEnum : JobEnum.values()) {
                for (String key : rpgPlayer.getJob(jobEnum).getList().keySet()) {
                    stmt = connection.prepareStatement("Update Job" + jobEnum.name() + " SET Count = ? WHERE Id = ? AND Object = ?");
                    stmt.setInt(1, rpgPlayer.getJob(jobEnum).getList().get(key));
                    stmt.setInt(2, rpgPlayer.getId());
                    stmt.setString(3, key);
                    stmt.executeUpdate();
                    stmt.close();
                }
            }
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized void setTime(int id) {
        try {
            Connection();
            // "UPDATE Worlds SET Creator = ? WHERE World = ?"
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy-HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String format = dtf.format(now);
            stmt = connection.prepareStatement("UPDATE RPGPlayer SET LastQuit = ? WHERE Id = ?");
            stmt.setString(1, format);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized void setGold(int id, double i) {
        try {
            Connection();
            // "UPDATE Worlds SET Creator = ? WHERE World = ?"
            stmt = connection.prepareStatement("UPDATE RPGPlayer SET Gold = ? WHERE Id = ?");
            stmt.setDouble(1, i);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized void setSpecie(int id, int i) {
        try {
            Connection();
            // "UPDATE Worlds SET Creator = ? WHERE World = ?"
            stmt = connection.prepareStatement("UPDATE RPGPlayer SET Specie = ? WHERE Id = ?");
            stmt.setInt(1, i);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized void setPlayerClass(int id, int i) {
        try {
            Connection();
            // "UPDATE Worlds SET Creator = ? WHERE World = ?"
            stmt = connection.prepareStatement("UPDATE RPGPlayer SET Class = ? WHERE Id = ?");
            stmt.setInt(1, i);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized void setTitle(int id, int i) {
        try {
            Connection();
            // "UPDATE Worlds SET Creator = ? WHERE World = ?"
            stmt = connection.prepareStatement("UPDATE RPGPlayer SET Title = ? WHERE Id = ?");
            stmt.setInt(1, i);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Select
    public synchronized void getGold(int id, Consumer consumer) {
        try {
            Connection();
            stmt = connection.prepareStatement("SELECT Gold FROM RPGPlayer WHERE Id = ?");
            stmt.setInt(2, id);
            ResultSet resultset = stmt.executeQuery();

            double gold = resultset.getDouble("Gold");

            resultset.close();
            close();
            consumer.accept(gold);
        } catch (SQLException resultset) {
            consumer.accept(0);
        }
    }


    public void Connection() {
        try {
            if(connection == null || connection.isClosed() || connection.isValid(0)) connect();
        } catch (SQLException ex) {
        }
    }

    public synchronized void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, user, password);
    }

    public synchronized void close() throws SQLException {
        if (connection != null || connection.isValid(0)) {
            connection.close();
            connection = null;
        }
        if (stmt != null) {
            stmt.close();
            stmt = null;
        }
    }

}