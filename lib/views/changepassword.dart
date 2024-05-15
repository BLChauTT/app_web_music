import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:spocify/app/globals.dart';
import 'package:spocify/utils/global.colors.dart';
import 'package:spocify/views/home.dart';
import 'package:spocify/views/widgets/text.form.global.dart';

import '../Helpers/account_helpers.dart';

class ChangePasswordPage extends StatefulWidget {
  @override
  _ChangePasswordPageState createState() => _ChangePasswordPageState();
}

class _ChangePasswordPageState extends State<ChangePasswordPage> {

  final TextEditingController currentPassword = TextEditingController();
  final TextEditingController passwordChange = TextEditingController();
  final TextEditingController CFpasswordChange = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
        gradient: LinearGradient(
            begin: Alignment.topCenter,
            end: Alignment.bottomCenter,
            colors: [
              GlobalColors.bottomColor.withOpacity(0.7),
              GlobalColors.subBlueColor.withOpacity(0.7),
            ]),
      ),
      child: Scaffold(
        backgroundColor: Colors.transparent,
        appBar: AppBar(
          automaticallyImplyLeading: false,
          backgroundColor: Colors.transparent,
          surfaceTintColor: Colors.transparent,
          elevation: 0,
          leading: Padding(
            padding: const EdgeInsets.only(left: 20.0),
            child: IconButton(
              onPressed: () {
                Navigator.pushReplacement(
                  context,
                  MaterialPageRoute(builder: (context) => HomePage()),
                );
              },
              icon: Icon(
                Icons.arrow_back_ios,
                color: GlobalColors.textColor,
              ),
            ),
          ),
          title: Center(
            child: Padding(
              padding: const EdgeInsets.only(right: 40.0),
              child: Text(
                "Change Password",
                style: TextStyle(
                  color: GlobalColors.textColor,
                  fontWeight: FontWeight.bold,
                  fontSize: 20.0,
                ),
              ),
            ),
          ),
        ),
        body: _changePasswordPage(),
      ),
    );
  }

  Widget _changePasswordPage() {

    return Scaffold(
      backgroundColor: Colors.transparent,
      body: SingleChildScrollView(
        child: SafeArea(
          child: Container(
            width: double.infinity,
            padding: EdgeInsets.all(30.0),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.start,
              children: [
                TextFormGlobal(
                  controller: currentPassword,
                  text: "Old Password",
                  obscure: true,
                  textInputType: TextInputType.text,
                ),
                const SizedBox(
                  height: 16.0,
                ),
                TextFormGlobal(
                  controller: passwordChange,
                  text: "New Password",
                  obscure: true,
                  textInputType: TextInputType.text,
                ),
                const SizedBox(
                  height: 16.0,
                ),
                TextFormGlobal(
                  controller: CFpasswordChange,
                  text: "Confirm New Password",
                  obscure: true,
                  textInputType: TextInputType.emailAddress,
                ),
                const SizedBox(
                  height: 16.0,
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
                      "Change Password",
                      style: TextStyle(
                        color: Colors.white,
                        fontWeight: FontWeight.w600,
                      ),
                    ),
                  ),
                  onTap: (){
                    changePassword();
                  },
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }

  void changePassword() async {
    String oldPassword = currentPassword.text;
    String newPassword = passwordChange.text;
    String confirmPassword = CFpasswordChange.text;

    // Kiểm tra xem mật khẩu cũ có đúng không
    bool isOldPasswordCorrect = await AccountDBHelper().checkLogin(usernameinlogin, oldPassword);
    if (!isOldPasswordCorrect) {
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
              'Old password is incorrect!',
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
      print('Old password is incorrect');
      return;
    }

    // Kiểm tra xem mật khẩu mới có trùng với mật khẩu cũ không
    if (oldPassword == newPassword) {
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
              'New password should not be the same as the old password!',
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
      print('New password should not be the same as the old password');
      return;
    }

    // Kiểm tra xem mật khẩu mới có ít nhất 6 ký tự không
    if (newPassword.length < 6) {
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
              'New password should be at least 6 characters long',
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
      print('New password should be at least 6 characters long');
      return;
    }

    // Kiểm tra xem mật khẩu mới và mật khẩu xác nhận có giống nhau không
    if (newPassword != confirmPassword) {
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
              'New password and confirm password do not match',
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
      print('New password and confirm password do not match');
      return;
    }

    // Thay đổi mật khẩu
    bool result = await AccountDBHelper().changePassword(usernameinlogin, newPassword);
    if (result) {
      showDialog(
        context: context,
        builder: (BuildContext context) {
          return AlertDialog(
            backgroundColor: GlobalColors.mainColor,
            title: Row(
              children: [
                Icon(
                  Icons.done_outline_outlined,
                  color: GlobalColors.textColor,
                ),
                const SizedBox(
                  width: 8.0,
                ),
                Text(
                  'Notification',
                  style: TextStyle(color: GlobalColors.textColor),
                ),
              ],
            ),
            content: Text(
              'Password changed successfully',
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
                  Navigator.pushReplacement(
                    context,
                    MaterialPageRoute(
                      builder: (context) => HomePage(),
                    ),
                  );
                },
              ),
            ],
          );
        },
      );
      print('Password changed successfully');
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
              'Failed to change password!',
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
      print('Failed to change password');
    }
  }

}
