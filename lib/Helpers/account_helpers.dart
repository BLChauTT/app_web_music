import 'dart:io';
import 'package:path_provider/path_provider.dart';
import 'package:sqflite/sqflite.dart';
import 'package:path/path.dart';

import '../entities/accounts.dart';

class AccountDBHelper {
  static Database? _db;

  static const String dbName = "accountdb";
  static const int dbVersion = 4;

  static const String accountTable = "account";

  static const String idColumn = "id";
  static const String usernameColumn = "username";
  static const String passwordColumn = "password";
  static const String preniumColumn = "prenium";
  static const String startPreColumn = "startPre";
  static const String endPreColumn = "endPre";

  Future<Database> get db async {
    if (_db != null) {
      return _db!;
    } else {
      _db = await initDB();
      return _db!;
    }
  }

  Future<Database> initDB() async {
    Directory directory = await getApplicationDocumentsDirectory();
    String path = join(directory.path, dbName);

    var database = openDatabase(
      path,
      version: dbVersion,
      onCreate: (db, version) => onCreateDB(db, version),
      onUpgrade: (db, oldVersion, newVersion) =>
          onUpgradeDB(db, oldVersion, newVersion),
    );

    if (_db == null) {
      _db = await database;
    }

    return _db!;
  }


  Future<void> onCreateDB(Database db, int version) async {
    await db.execute("create table $accountTable("
        "$idColumn integer primary key autoincrement, "
        "$usernameColumn text, "
        "$passwordColumn text, "
        "$preniumColumn bool, "
        "$startPreColumn text, "
        "$endPreColumn text"
        ")");
  }

  void onUpgradeDB(Database db, int oldVersion, int newVersion) async {
    await db.execute("drop table $accountTable");
    onCreateDB(db, newVersion);
  }

  Future<bool> createAcc(Account account) async {
    var dbClient = await db;
    return await dbClient.insert(accountTable, account.toMap()) > 0;
  }

  Future<bool> update(Account account) async {
    var dbClient = await db;
    return await dbClient.update(
      accountTable,
      account.toMap(),
      where: "$idColumn = ?",
      whereArgs: [account.id!],
    ) >
        0;
  }

  Future<bool> delete(int id) async {
    var dbClient = await db;
    return await dbClient.delete(
      accountTable,
      where: "$idColumn = ?",
      whereArgs: [id],
    ) >
        0;
  }

  Future<List<Account>> findAll() async {
    var dbClient = await db;
    var maps = await dbClient.query(accountTable);
    List<Account> contacts = [];
    if (maps.isNotEmpty) {
      for (var map in maps) {
        contacts.add(Account.fromMap(map));
      }
    }
    return contacts;
  }

  Future<bool> checkLogin(String username, String password) async {
    var dbClient = await db;
    var maps = await dbClient.query(
      accountTable,
      where: "$usernameColumn = ? AND $passwordColumn = ?",
      whereArgs: [username, password],
    );
    return maps.isNotEmpty;
  }

  Future<bool> checkUsername(String username) async {
    var dbClient = await db;
    var maps = await dbClient.query(
      accountTable,
      where: "$usernameColumn = ?",
      whereArgs: [username],
    );
    return maps.isNotEmpty;
  }

  Future<bool> changePassword(String username, String newPassword) async {
    var dbClient = await db;
    Account? account = await getAccountByUsername(username);
    if (account != null) {
      account.password = newPassword;
      return await update(account);
    }
    return false;
  }

  Future<Account?> getAccountByUsername(String username) async {
    var dbClient = await db;
    var maps = await dbClient.query(
      accountTable,
      where: "$usernameColumn = ?",
      whereArgs: [username],
    );
    if (maps.isNotEmpty) {
      return Account.fromMap(maps.first);
    }
    return null;
  }



}
