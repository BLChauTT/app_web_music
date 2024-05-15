import 'package:flutter/material.dart';
import 'package:spocify/Helpers/account_detail_helpers.dart';
import 'package:spocify/Helpers/account_helpers.dart';
import 'package:spocify/app/globals.dart';
import 'package:spocify/entities/account_details.dart';
import 'package:spocify/entities/accounts.dart';
import 'package:spocify/utils/global.colors.dart';
import 'package:spocify/views/login.dart';
import 'package:spocify/views/widgets/social.login.dart';
import 'package:spocify/views/widgets/text.form.global.dart';

class RegisterPage extends StatelessWidget {
  RegisterPage({Key? key}) : super(key: key);
  final TextEditingController username = TextEditingController();
  final TextEditingController password = TextEditingController();
  final TextEditingController CFpassword = TextEditingController();

  var db = AccountDBHelper();
  var dbdetail = AccountDetailDBHelper();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: GlobalColors.whiteColor,
      body: SingleChildScrollView(
        child: SafeArea(
          child: Container(
            width: double.infinity,
            padding: const EdgeInsets.all(30),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                const SizedBox(
                  height: 100,
                ),
                Container(
                  alignment: Alignment.center,
                  child: Text(
                    "Umisic",
                    style: TextStyle(
                      color: GlobalColors.mainColor,
                      fontWeight: FontWeight.bold,
                      fontSize: 35,
                    ),
                  ),
                ),
                const SizedBox(
                  height: 50,
                ),
                Text(
                  "Create your account",
                  style: TextStyle(
                    color: GlobalColors.mainColor.withOpacity(0.7),
                    fontWeight: FontWeight.w500,
                    fontSize: 20,
                  ),
                ),
                const SizedBox(
                  height: 15,
                ),
                //Email Input
                TextFormGlobal(
                  controller: username,
                  text: "Username",
                  obscure: false,
                  textInputType: TextInputType.emailAddress,
                ),
                const SizedBox(
                  height: 10,
                ),
                //Password Input
                TextFormGlobal(
                  controller: password,
                  text: "Password",
                  obscure: true,
                  textInputType: TextInputType.text,
                ),
                const SizedBox(
                  height: 10,
                ),
                TextFormGlobal(
                  controller: CFpassword,
                  text: "Confirm Password",
                  obscure: true,
                  textInputType: TextInputType.text,
                ),
                const SizedBox(
                  height: 20,
                ),
                InkWell(
                  child: Container(
                    alignment: Alignment.center,
                    height: 55,
                    decoration: BoxDecoration(
                      color: GlobalColors.mainColor,
                      borderRadius: BorderRadius.circular(6),
                      boxShadow: [
                        BoxShadow(
                          color: Colors.black.withOpacity(0.1),
                          blurRadius: 10,
                        ),
                      ],
                    ),
                    child: const Text(
                      "Sign Up",
                      style: TextStyle(
                        color: Colors.white,
                        fontWeight: FontWeight.w600,
                      ),
                    ),
                  ),
                  onTap: () => signup(context),
                ),
                const SizedBox(
                  height: 50,
                ),
                const SocialLogin(
                  text: "-Or sign up with-",
                ),
              ],
            ),
          ),
        ),
      ),
      bottomNavigationBar: Container(
        height: 50,
        color: GlobalColors.whiteColor,
        alignment: Alignment.center,
        child: Row(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            const Text(
              "Have an account? ",
            ),
            InkWell(
              child: Text(
                "Login",
                style: TextStyle(
                  color: GlobalColors.mainColor,
                  fontWeight: FontWeight.bold,
                ),
              ),
              onTap: () => {
                Navigator.pushReplacement(
                  context,
                  MaterialPageRoute(
                    builder: (context) => LoginPage(),
                  ),
                )
              },
            ),
          ],
        ),
      ),
    );
  }

  void signup(BuildContext context) async {
    // Kiểm tra username có ít nhất 5 kí tự
    if (username.text.length < 5) {
      showDialog(
        context: context,
        builder: (BuildContext context) {
          return AlertDialog(
            backgroundColor: GlobalColors.mainColor,
            title: Row(
              children: [
                Icon(
                  Icons.report_gmailerrorred,
                  color: GlobalColors.textColor,
                ),
                const SizedBox(
                  width: 8.0,
                ),
                Text(
                  'Error',
                  style: TextStyle(color: GlobalColors.textColor),
                ),
              ],
            ),
            content: Text(
              'Username must be at least 5 characters!',
              style: TextStyle(
                color: GlobalColors.textColor,
                fontSize: 16.0,
              ),
            ),
            actions: <Widget>[
              TextButton(
                child: Text(
                  'OK',
                  style: TextStyle(color: GlobalColors.textColor),
                ),
                onPressed: () {
                  Navigator.of(context).pop();
                },
              ),
            ],
          );
        },
      );
      return;
    }

    // Kiểm tra password có ít nhất 6 kí tự
    if (password.text.length < 6) {
      showDialog(
        context: context,
        builder: (BuildContext context) {
          return AlertDialog(
            backgroundColor: GlobalColors.mainColor,
            title: Row(
              children: [
                Icon(
                  Icons.report_gmailerrorred,
                  color: GlobalColors.textColor,
                ),
                const SizedBox(
                  width: 8.0,
                ),
                Text(
                  'Error',
                  style: TextStyle(color: GlobalColors.textColor),
                ),
              ],
            ),
            content: Text(
              'Password must be at least 6 characters!',
              style: TextStyle(
                color: GlobalColors.textColor,
                fontSize: 16.0,
              ),
            ),
            actions: <Widget>[
              TextButton(
                child: Text(
                  'OK',
                  style: TextStyle(color: GlobalColors.textColor),
                ),
                onPressed: () {
                  Navigator.of(context).pop();
                },
              ),
            ],
          );
        },
      );
      return;
    }

    // Kiểm tra password và confirm password có giống nhau
    if (password.text != CFpassword.text) {
      showDialog(
        context: context,
        builder: (BuildContext context) {
          return AlertDialog(
            backgroundColor: GlobalColors.mainColor,
            title: Row(
              children: [
                Icon(
                  Icons.report_gmailerrorred,
                  color: GlobalColors.textColor,
                ),
                const SizedBox(
                  width: 8.0,
                ),
                Text(
                  'Error',
                  style: TextStyle(color: GlobalColors.textColor),
                ),
              ],
            ),
            content: Text(
              'Your confirm password not like password!',
              style: TextStyle(
                color: GlobalColors.textColor,
                fontSize: 16.0,
              ),
            ),
            actions: <Widget>[
              TextButton(
                child: Text(
                  'OK',
                  style: TextStyle(color: GlobalColors.textColor),
                ),
                onPressed: () {
                  Navigator.of(context).pop();
                },
              ),
            ],
          );
        },
      );
      return;
    }

    // Kiểm tra username đã tồn tại
    if (await db.checkUsername(username.text)) {
      showDialog(
        context: context,
        builder: (BuildContext context) {
          return AlertDialog(
            backgroundColor: GlobalColors.mainColor,
            title: Row(
              children: [
                Icon(
                  Icons.report_gmailerrorred,
                  color: GlobalColors.textColor,
                ),
                SizedBox(
                  width: 8.0,
                ),
                Text(
                  'Error',
                  style: TextStyle(color: GlobalColors.textColor),
                ),
              ],
            ),
            content: Text(
              'Username already exists!',
              style: TextStyle(
                color: GlobalColors.textColor,
                fontSize: 16.0,
              ),
            ),
            actions: <Widget>[
              TextButton(
                child: Text(
                  'OK',
                  style: TextStyle(color: GlobalColors.textColor),
                ),
                onPressed: () {
                  Navigator.of(context).pop();
                },
              ),
            ],
          );
        },
      );
      return;
    }

    var acc = Account(
      username: username.text,
      password: password.text,
    );

    var accountDetail = AccountDetail(
      username: username.text,
      firstname: "",
      lastname: "",
      email: "",
      age: "",
      country: "",
    );

    if (await db.createAcc(acc)) {
      await dbdetail.createAccDetail(accountDetail);

      var accountDetails = await dbdetail.findByUsername(username.text);

      Navigator.push(
        context,
        MaterialPageRoute(
          builder: (context) => LoginPage(),
        ),
      );
    }
  }

}
