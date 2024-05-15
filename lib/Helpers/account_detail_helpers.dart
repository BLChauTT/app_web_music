import 'dart:io';
import 'package:path_provider/path_provider.dart';
import 'package:spocify/entities/account_details.dart';
import 'package:sqflite/sqflite.dart';
import 'package:path/path.dart';

class AccountDetailDBHelper {
  static Database? _db;

  static const String dbName = "accountdetailsdb";
  static const int dbVersion = 27;

  static const String accountDetailsTable = "account_details";

  static const String usernameColumn = "username";
  static const String idColumn = "id";
  static const String firstnameColumn = "firstname";
  static const String lastnameColumn = "lastname";
  static const String emailColumn = "email";
  static const String ageColumn = "age";
  static const String countryColumn = "country";

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
    await db.execute("CREATE TABLE $accountDetailsTable("
        "$idColumn INTEGER PRIMARY KEY AUTOINCREMENT, "
        "$usernameColumn TEXT, "
        "$firstnameColumn TEXT, "
        "$lastnameColumn TEXT, "
        "$emailColumn TEXT, "
        "$ageColumn TEXT, "
        "$countryColumn TEXT"
        ")");
  }

  void onUpgradeDB(Database db, int oldVersion, int newVersion) async {
    await db.execute("drop table $accountDetailsTable");
    onCreateDB(db, newVersion);
  }

  Future<bool> createAccDetail(AccountDetail accountDetail) async {
    var dbClient = await db;
    return await dbClient.insert(
            AccountDetailDBHelper.accountDetailsTable, accountDetail.toMap()) >
        0;
  }

  Future<AccountDetail?> findByUsername(String username) async {
    var dbClient = await db;
    var map = await dbClient.query(
      accountDetailsTable,
      where: "$usernameColumn like ?",
      whereArgs: ["%$username%"],
    );

    return AccountDetail.fromMap(map.first);
  }

  Future<bool> deleteAndCreateNewAccountDetail(
      AccountDetail accountDetail) async {
    var dbClient = await db;

    await dbClient.delete(
      accountDetailsTable,
      where: "$idColumn = ?",
      whereArgs: [accountDetail.id!],
    );

    await dbClient.insert(accountDetailsTable, accountDetail.toMap());

    return true;
  }

}
