import 'package:flutter/material.dart';
import 'package:spocify/utils/global.colors.dart';
import 'package:spocify/views/register.dart';
import 'package:spocify/views/widgets/button.form.global.dart';
import 'package:spocify/views/widgets/social.login.dart';
import 'package:spocify/views/widgets/text.form.global.dart';

class LoginPage extends StatelessWidget {
  LoginPage({Key? key}) : super(key: key);
  final TextEditingController emailController = TextEditingController();
  final TextEditingController passwordController = TextEditingController();

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
                    color: GlobalColors.textColor,
                    fontWeight: FontWeight.w500,
                    fontSize: 20,
                  ),
                ),
                const SizedBox(
                  height: 15,
                ),
                //Email Input
                TextFormGlobal(
                  controller: emailController,
                  text: "Email",
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
                const ButtonGlobal(
                  text: "Sign in",
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
            Text(
              "Don\'t have an account? ",
            ),
            InkWell(
              child: Text(
                "Sign Up",
                style: TextStyle(
                  color: GlobalColors.mainColor,
                ),
              ),
              onTap: () => {
                Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) => RegisterPage(),
                  ),
                )
              },
            ),
          ],
        ),
      ),
    );
  }
}
