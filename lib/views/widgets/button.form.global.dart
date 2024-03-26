import 'package:flutter/material.dart';
import 'package:spocify/utils/global.colors.dart';
import 'package:spocify/views/home.dart';

class ButtonGlobal extends StatefulWidget {
  const ButtonGlobal({
    Key? key,
    required this.text,
    required this.myVoidCallback
  }) : super(key: key);
  final String text;
  final VoidCallback myVoidCallback;
  @override
  _ButtonGlobalState createState() => _ButtonGlobalState();
}

class _ButtonGlobalState extends State<ButtonGlobal> {
  @override
  Widget build(BuildContext context) {
    return InkWell(
      onTap: widget.myVoidCallback,
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
        child: Text(
          widget.text,
          style: TextStyle(
            color: Colors.white,
            fontWeight: FontWeight.w600,
          ),
        ),
      ),
    );
  }

  void signup() {
    // Xử lý logic khi nhấn nút Sign Up

  }

  void login() {
    // Xử lý logic khi nhấn nút Login
    Navigator.push(
      context,
      MaterialPageRoute(
        builder: (context) => HomePage(),
      ),
    );
  }
}
