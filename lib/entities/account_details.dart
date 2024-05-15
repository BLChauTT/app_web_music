import 'package:intl/intl.dart';

class AccountDetail {
  String? username;
  int? id;
  String? firstname;
  String? lastname;
  String? email;
  String? age;
  String? country;

  AccountDetail({
    this.username,
    this.id,
    this.firstname,
    this.lastname,
    this.email,
    this.age,
    this.country,
  });

  AccountDetail.fromMap(Map<String, dynamic> map) {
    id = map["id"] ?? "";
    username = map["username"] ?? "";
    firstname = map["firstname"] ?? "";
    lastname = map["lastname"] ?? "";
    email = map["email"] ?? "";
    age = map["age"] ?? "";
    country = map["country"] ?? "";
  }

  Map<String, dynamic> toMap() {
    return <String, dynamic>{
      "id" : id,
      "username" : username,
      "firstname" : firstname,
      "lastname" : lastname,
      "email" : email,
      "age" : age,
      "country" : country,
    };
  }


}