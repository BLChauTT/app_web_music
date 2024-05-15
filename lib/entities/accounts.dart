import 'package:intl/intl.dart';

class Account {
  int? id;
  String? username;
  String? password;
  bool? prenium;
  late DateTime? startPre;
  late DateTime? endPre;

  Account({
    this.id,
    this.username,
    this.password,
    this.prenium,
    this.startPre,
    this.endPre,
  });

  Account.fromMap(Map<String, dynamic> map) {
    var dateFormat = DateFormat("yyyy-MM-dd");

    id = map["id"];
    username = map["username"];
    password = map["password"];
    prenium = map["prenium"];
    startPre = map["startPre"] != null ? dateFormat.parse(map["startPre"]) : null;
    endPre = map["endPre"] != null ? dateFormat.parse(map["endPre"]) : null;
  }

  Map<String, dynamic> toMap() {
    var dateFormat = DateFormat("yyyy-MM-dd");
    return <String, dynamic>{
      "id" : id,
      "username" : username,
      "password" : password,
      "prenium" : prenium,
      "startPre" : startPre != null ? dateFormat.format(startPre!) : null,
      "endPre" : endPre != null ? dateFormat.format(endPre!) : null,
    };
  }


}