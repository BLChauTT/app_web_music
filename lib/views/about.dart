import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:spocify/utils/global.colors.dart';
import 'package:spocify/views/home.dart';

class AboutPage extends StatefulWidget {
  @override
  _AboutPageState createState() => _AboutPageState();
}

class _AboutPageState extends State<AboutPage> {
  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
        gradient: LinearGradient(
            begin: Alignment.topCenter,
            end: Alignment.bottomCenter,
            colors: [
              GlobalColors.bottomColor.withOpacity(0.9),
              GlobalColors.subBlueColor.withOpacity(0.8),
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
              padding: const EdgeInsets.only(right: 24.0),
              child: Text(
                "About",
                style: TextStyle(
                  color: GlobalColors.textColor,
                  fontWeight: FontWeight.bold,
                  fontSize: 20.0,
                ),
              ),
            ),
          ),
        ),
        body: _aboutPage(),
      ),
    );
  }

  Widget _aboutPage() {
    return Scaffold(
      backgroundColor: Colors.transparent,
      body: Padding(
        padding: const EdgeInsets.symmetric(horizontal: 24.0),
        child: Column(
          children: [
            SizedBox(
              height: 16.0,
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Text(
                  "Version",
                  style: TextStyle(
                    color: GlobalColors.textColor,
                    fontWeight: FontWeight.w500,
                    fontSize: 14.0,
                  ),
                ),
                Text(
                  "1.0.36.666",
                  style: TextStyle(
                    color: GlobalColors.textColor.withOpacity(0.4),
                    fontWeight: FontWeight.w500,
                    fontSize: 16.0,
                  ),
                ),
              ],
            ),
            const SizedBox(
              height: 16.0,
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Column(
                  mainAxisAlignment: MainAxisAlignment.start,
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      "Terms and conditions",
                      style: TextStyle(
                        color: GlobalColors.textColor,
                        fontWeight: FontWeight.w500,
                        fontSize: 16.0,
                      ),
                    ),
                    Text(
                      "All the stuff you need to know",
                      style: TextStyle(
                        color: GlobalColors.textColor.withOpacity(0.5),
                        fontWeight: FontWeight.w500,
                        fontSize: 12.0,
                      ),
                    ),
                  ],
                ),
                Icon(
                  CupertinoIcons.share_up,
                  color: GlobalColors.textColor.withOpacity(0.4),
                )
              ],
            ),
            const SizedBox(
              height: 16.0,
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Column(
                  mainAxisAlignment: MainAxisAlignment.start,
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      "Privacy policy",
                      style: TextStyle(
                        color: GlobalColors.textColor,
                        fontWeight: FontWeight.w500,
                        fontSize: 16.0,
                      ),
                    ),
                    Text(
                      "Important for both of us",
                      style: TextStyle(
                        color: GlobalColors.textColor.withOpacity(0.5),
                        fontWeight: FontWeight.w500,
                        fontSize: 12.0,
                      ),
                    ),
                  ],
                ),
                Icon(
                  CupertinoIcons.share_up,
                  color: GlobalColors.textColor.withOpacity(0.4),
                )
              ],
            ),
            const SizedBox(
              height: 16.0,
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Column(
                  mainAxisAlignment: MainAxisAlignment.start,
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      "Platform rules",
                      style: TextStyle(
                        color: GlobalColors.textColor,
                        fontWeight: FontWeight.w500,
                        fontSize: 16.0,
                      ),
                    ),
                    Text(
                      "Help keep Umisic safe for all",
                      style: TextStyle(
                        color: GlobalColors.textColor.withOpacity(0.5),
                        fontWeight: FontWeight.w500,
                        fontSize: 12.0,
                      ),
                    ),
                  ],
                ),
                Icon(
                  CupertinoIcons.share_up,
                  color: GlobalColors.textColor.withOpacity(0.4),
                )
              ],
            ),
            const SizedBox(
              height: 16.0,
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Column(
                  mainAxisAlignment: MainAxisAlignment.start,
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      "Support",
                      style: TextStyle(
                        color: GlobalColors.textColor,
                        fontWeight: FontWeight.w500,
                        fontSize: 16.0,
                      ),
                    ),
                    Text(
                      "Get help from us and the community",
                      style: TextStyle(
                        color: GlobalColors.textColor.withOpacity(0.5),
                        fontWeight: FontWeight.w500,
                        fontSize: 12.0,
                      ),
                    ),
                  ],
                ),
                Icon(
                  CupertinoIcons.share_up,
                  color: GlobalColors.textColor.withOpacity(0.4),
                )
              ],
            ),
          ],
        ),
      ),
    );
  }
}
