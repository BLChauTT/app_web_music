import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_svg/svg.dart';
import 'package:spocify/utils/global.colors.dart';

class SocialLogin extends StatefulWidget {
  const SocialLogin({
    Key? key,
    required this.text,
  }) : super(key: key);
  final String text;

  @override
  _SocialLoginState createState() => _SocialLoginState();
}

class _SocialLoginState extends State<SocialLogin> {
  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Container(
          alignment: Alignment.center,
          child: Text(
            widget.text,
            style: TextStyle(
              color: GlobalColors.mainColor.withOpacity(0.7),
              fontWeight: FontWeight.w600,
            ),
          ),
        ),
        const SizedBox(
          height: 15,
        ),
        Container(
          width: MediaQuery.of(context).size.width * 0.8,
          child: Row(
            children: [
              //Google
              Expanded(
                child: Container(
                  alignment: Alignment.center,
                  height: 55,
                  decoration: BoxDecoration(
                    color: GlobalColors.whiteColor,
                    borderRadius: BorderRadius.circular(6),
                    boxShadow: [
                      BoxShadow(
                        color: Colors.black.withOpacity(0.1),
                        blurRadius: 10,
                      ),
                    ],
                  ),
                  child: SvgPicture.asset(
                    "assets/images/google.svg",
                    height: 25,
                  ),
                ),
              ),
              const SizedBox(
                width: 10,
              ),
              //Facebook
              Expanded(
                child: Container(
                  alignment: Alignment.center,
                  height: 55,
                  decoration: BoxDecoration(
                    color: GlobalColors.whiteColor,
                    borderRadius: BorderRadius.circular(6),
                    boxShadow: [
                      BoxShadow(
                        color: Colors.black.withOpacity(0.1),
                        blurRadius: 10,
                      ),
                    ],
                  ),
                  child: SvgPicture.asset(
                    "assets/images/facebook.svg",
                    height: 25,
                  ),
                ),
              ),
              const SizedBox(
                width: 10,
              ),
              //twitter
              Expanded(
                child: Container(
                  alignment: Alignment.center,
                  height: 55,
                  decoration: BoxDecoration(
                    color: GlobalColors.whiteColor,
                    borderRadius: BorderRadius.circular(6),
                    boxShadow: [
                      BoxShadow(
                        color: Colors.black.withOpacity(0.1),
                        blurRadius: 10,
                      ),
                    ],
                  ),
                  child: SvgPicture.asset(
                    "assets/images/twitter.svg",
                    height: 25,
                  ),
                ),
              ),
            ],
          ),
        ),
      ],
    );
  }
}
