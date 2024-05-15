import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:spocify/utils/global.colors.dart';
import 'package:spocify/views/home.dart';
import 'package:spocify/views/uploadmusic.dart';

class ProfilePage extends StatefulWidget {
  @override
  _ProfilePageState createState() => _ProfilePageState();
}

class _ProfilePageState extends State<ProfilePage> {
  bool isPremium = false;
  bool isLike = false;

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
          actions: [
            Padding(
              padding: const EdgeInsets.only(right: 10.0),
              child: IconButton(
                  onPressed: () {},
                  icon: Icon(
                    Icons.share,
                    color: GlobalColors.textColor,
                  )),
            )
          ],
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
        ),
        body: _profilePage(),
      ),
    );
  }

  Widget _profilePage() {
    return Scaffold(
      backgroundColor: Colors.transparent,
      floatingActionButton: Container(
        decoration: BoxDecoration(
            color: GlobalColors.textColor.withOpacity(0.85),
            borderRadius: BorderRadius.circular(
              4.0,
            )),
        child: IconButton(
          onPressed: () {
            Navigator.push(
              context,
              MaterialPageRoute(
                builder: (context) => UploadPage(),
              ),
            );
          },
          icon: Icon(
            Icons.upload,
            color: GlobalColors.mainColor,
          ),
        ),
      ),
      body: Column(
        children: [
          Container(
            color: Colors.transparent,
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.center,
              children: [
                Stack(
                  alignment: Alignment.bottomRight,
                  children: [
                    CircleAvatar(
                      radius: 50,
                      backgroundImage: AssetImage("assets/images/sagiri-1.png"),
                      backgroundColor: GlobalColors.textColor,
                    ),
                    InkWell(
                      onTap: () {},
                      child: CircleAvatar(
                        radius: 12,
                        backgroundColor: GlobalColors.mainColor,
                        child: Icon(
                          Icons.edit,
                          size: 16,
                          color: GlobalColors.textColor,
                        ),
                      ),
                    ),
                  ],
                ),
                Padding(
                  padding: const EdgeInsets.all(16.0),
                  child: Text(
                    "TDUmi#2024",
                    style: Theme.of(context).textTheme.titleMedium!.copyWith(
                          color: GlobalColors.textColor,
                          fontWeight: FontWeight.bold,
                          // fontSize: 16.0,
                        ),
                  ),
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceAround,
                  children: [
                    Column(
                      children: [
                        Text(
                          "0",
                          style:
                              Theme.of(context).textTheme.titleLarge!.copyWith(
                                    color: GlobalColors.textColor,
                                    fontWeight: FontWeight.bold,
                                  ),
                        ),
                        Text(
                          "Recipes",
                          style:
                              Theme.of(context).textTheme.subtitle2!.copyWith(
                                    color: GlobalColors.textColor,
                                  ),
                        ),
                      ],
                    ),
                    Column(
                      children: [
                        Text(
                          "0",
                          style:
                              Theme.of(context).textTheme.titleLarge!.copyWith(
                                    color: GlobalColors.textColor,
                                    fontWeight: FontWeight.bold,
                                  ),
                        ),
                        Text(
                          "Following",
                          style:
                              Theme.of(context).textTheme.subtitle2!.copyWith(
                                    color: GlobalColors.textColor,
                                  ),
                        ),
                      ],
                    ),
                    Column(
                      children: [
                        Text(
                          "0",
                          style:
                              Theme.of(context).textTheme.titleLarge!.copyWith(
                                    color: GlobalColors.textColor,
                                    fontWeight: FontWeight.bold,
                                  ),
                        ),
                        Text(
                          "Followers",
                          style:
                              Theme.of(context).textTheme.subtitle2!.copyWith(
                                    color: GlobalColors.textColor,
                                  ),
                        ),
                      ],
                    ),
                  ],
                ),
              ],
            ),
          ),

          // Container(
          //   color: Colors.transparent,
          //   padding: const EdgeInsets.symmetric(horizontal: 20.0),
          //   child: Stack(
          //     children: [
          //       GestureDetector(
          //         onTap: () {
          //           setState(() {
          //             isLike = !isLike;
          //             print(isLike);
          //           });
          //         },
          //         child: Container(
          //           height: 50,
          //           width: MediaQuery.of(context).size.width,
          //           decoration: BoxDecoration(
          //             color: GlobalColors.whiteColor,
          //           ),
          //           child: Stack(
          //             children: [
          //               AnimatedPositioned(
          //                 duration: const Duration(milliseconds: 200),
          //                 left: isLike
          //                     ? 0
          //                     : MediaQuery.of(context).size.width * 0.453,
          //                 child: Container(
          //                   height: 50,
          //                   width:
          //                   MediaQuery.of(context).size.width * 0.45,
          //                   decoration: BoxDecoration(
          //                     color: GlobalColors.mainColor
          //                         .withOpacity(0.85),
          //                   ),
          //                 ),
          //               ),
          //               Row(
          //                 children: [
          //                   Expanded(
          //                     child: Row(
          //                       mainAxisAlignment:
          //                       MainAxisAlignment.center,
          //                       children: [
          //                         Center(
          //                           child: Text(
          //                             "Play",
          //                             style: TextStyle(
          //                               color: isLike
          //                                   ? GlobalColors.whiteColor
          //                                   : GlobalColors.mainColor,
          //                               fontSize: 17,
          //                               fontWeight: FontWeight.w600,
          //                             ),
          //                           ),
          //                         ),
          //                       ],
          //                     ),
          //                   ),
          //                   Expanded(
          //                     child: Row(
          //                       mainAxisAlignment:
          //                       MainAxisAlignment.center,
          //                       children: [
          //                         Center(
          //                           child: Text(
          //                             "Shuffle",
          //                             style: TextStyle(
          //                               color: isLike
          //                                   ? GlobalColors.mainColor
          //                                   : GlobalColors.whiteColor,
          //                               fontSize: 17,
          //                               fontWeight: FontWeight.w600,
          //                             ),
          //                           ),
          //                         ),
          //                       ],
          //                     ),
          //                   ),
          //                 ],
          //               ),
          //             ],
          //           ),
          //         ),
          //       ),
          //     ],
          //   ),
          // ),
        ],
      ),
    );
  }
}
