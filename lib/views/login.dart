import 'package:flutter/material.dart';
import 'package:spocify/app/globals.dart';
import 'package:spocify/utils/global.colors.dart';
import 'package:spocify/views/home.dart';
import 'package:spocify/views/register.dart';
import 'package:spocify/views/widgets/social.login.dart';
import 'package:spocify/views/widgets/text.form.global.dart';

import '../Helpers/account_detail_helpers.dart';
import '../Helpers/account_helpers.dart';

class LoginPage extends StatelessWidget {
  LoginPage({Key? key}) : super(key: key);
  final TextEditingController usernameController = TextEditingController();
  final TextEditingController passwordController = TextEditingController();

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
                  "Login to your account",
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
                  controller: usernameController,
                  text: "Username",
                  obscure: false,
                  textInputType: TextInputType.emailAddress,
                ),
                const SizedBox(
                  height: 10,
                ),
                //Password Input
                TextFormGlobal(
                  controller: passwordController,
                  text: "Password",
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
                      "Sign In",
                      style: TextStyle(
                        color: Colors.white,
                        fontWeight: FontWeight.w600,
                      ),
                    ),
                  ),
                  onTap: () => signin(context),
                ),
                const SizedBox(
                  height: 75,
                ),
                SocialLogin(
                  text: "-Or sign in with-",
                ),
              ],
            ),
          ),
        ),
      ),
      bottomNavigationBar: Container(
        height: 150,
        color: GlobalColors.whiteColor,
        alignment: Alignment.center,
        child: Row(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            const Text(
              "Don't have an account? ",
            ),
            InkWell(
              child: Text(
                "Sign Up",
                style: TextStyle(
                  color: GlobalColors.mainColor,
                  fontWeight: FontWeight.bold,
                ),
              ),
              onTap: () => {
                Navigator.pushAndRemoveUntil(
                  context,
                  MaterialPageRoute(
                    builder: (context) => RegisterPage(),
                  ),
                  (Route<dynamic> route) => false,
                )
              },
            ),
          ],
        ),
      ),
    );
  }

  Future<void> signin(BuildContext context) async {
    if (await db.checkLogin(usernameController.text, passwordController.text)) {
      usernameinlogin = usernameController.text;
      // print(usernameController.text);
      // print(usernameinlogin);
      Navigator.push(
        context,
        MaterialPageRoute(
          builder: (context) => HomePage(),
        ),
      );
    } else {
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
              'Username or password cannot be empty or invalid!',
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
    }
  }
}
