import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:spocify/utils/global.colors.dart';

class PremiumPage extends StatefulWidget {
  @override
  _PremiumPageState createState() => _PremiumPageState();
}

class _PremiumPageState extends State<PremiumPage> {
  bool isPremium = false;

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
          backgroundColor: Colors.transparent,
          surfaceTintColor: Colors.transparent,
          title: Row(
            children: [
              Icon(
                Icons.workspace_premium_sharp,
                size: 30,
                color: GlobalColors.textColor,
              ),
              Text(
                'Premium',
                style: TextStyle(
                  color: GlobalColors.textColor,
                  fontWeight: FontWeight.bold,
                ),
              ),
            ],
          ),
          automaticallyImplyLeading: false,
        ),
        body: isPremium ? showPremiumContent() : showSubscriptionOptions(),
      ),
    );
  }

  Widget showSubscriptionOptions() {
    return Scaffold(
      extendBodyBehindAppBar: false,
      backgroundColor: Colors.transparent,
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: SingleChildScrollView(
          child: Column(
            children: [
              Container(
                decoration: BoxDecoration(
                  color: GlobalColors.cardColor,
                  borderRadius: BorderRadius.circular(10),
                ),
                child: Padding(
                  padding: const EdgeInsets.all(12.0),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Padding(
                        padding: const EdgeInsets.only(bottom: 4.0),
                        child: Text(
                          'Premium',
                          style: TextStyle(
                            color: GlobalColors.textColor,
                            fontWeight: FontWeight.bold,
                            fontSize: 12,
                          ),
                        ),
                      ),
                      const Text(
                        "Mini",
                        style: TextStyle(
                          color: Colors.lightGreenAccent,
                          fontWeight: FontWeight.bold,
                          fontSize: 20,
                        ),
                      ),
                      Text(
                        "0.09\$ for 1 day\n0.35\$ for 1 week",
                        style: TextStyle(
                          color: GlobalColors.textColor,
                          fontWeight: FontWeight.bold,
                          fontSize: 16,
                        ),
                      ),
                      Divider(
                        color: GlobalColors.greyColor,
                      ),
                      Padding(
                        padding: const EdgeInsets.only(left: 16.0, top: 8.0),
                        child: Text(
                          "• 1 mobile-only Premium account\n• Offline listening of up to 30 songs on 1 device\n• On-time payment\n• Basic audio quality",
                          style: TextStyle(
                            color: GlobalColors.textColor,
                            fontSize: 16,
                          ),
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.all(16.0),
                        child: Center(
                          child: Container(
                            width: double.infinity,
                            child: ElevatedButton(
                              style: ElevatedButton.styleFrom(
                                backgroundColor: Colors.lightGreen,
                              ),
                              onPressed: () {},
                              child: const Text(
                                "One-time payment",
                                style: TextStyle(
                                  color: Colors.black,
                                  fontSize: 16,
                                  fontWeight: FontWeight.bold,
                                ),
                              ),
                            ),
                          ),
                        ),
                      ),
                    ],
                  ),
                ),
              ),
              const SizedBox(
                height: 24,
              ),
              Container(
                decoration: BoxDecoration(
                  color: GlobalColors.cardColor,
                  borderRadius: BorderRadius.circular(10),
                ),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Container(
                      decoration: BoxDecoration(
                        color: GlobalColors.pinkColorLow,
                        borderRadius: const BorderRadius.only(
                          topLeft: Radius.circular(10.0),
                          bottomRight: Radius.circular(10.0),
                        ),
                      ),
                      child: Padding(
                        padding: const EdgeInsets.only(
                          top: 4.0,
                          bottom: 4.0,
                          left: 12.0,
                          right: 12.0,
                        ),
                        child: Text(
                          "2.33\$ for 3 months",
                          style: TextStyle(
                            color: GlobalColors.blackColor,
                            fontWeight: FontWeight.bold,
                            fontSize: 14,
                          ),
                        ),
                      ),
                    ),
                    Padding(
                      padding: const EdgeInsets.all(12.0),
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Padding(
                            padding: const EdgeInsets.only(bottom: 4.0),
                            child: Text(
                              'Premium',
                              style: TextStyle(
                                color: GlobalColors.textColor,
                                fontWeight: FontWeight.bold,
                                fontSize: 12,
                              ),
                            ),
                          ),
                          Text(
                            "Individual",
                            style: TextStyle(
                              color: GlobalColors.pinkColorLow,
                              fontWeight: FontWeight.bold,
                              fontSize: 20,
                            ),
                          ),
                          Text(
                            "2.33\$ for 3 months",
                            style: TextStyle(
                              color: GlobalColors.textColor,
                              fontWeight: FontWeight.bold,
                              fontSize: 16,
                            ),
                          ),
                          Text(
                            "2.33\$/month after",
                            style: TextStyle(
                              color: GlobalColors.subTextColor,
                              fontSize: 14,
                            ),
                          ),
                          Divider(
                            color: GlobalColors.greyColor,
                          ),
                          Padding(
                            padding:
                                const EdgeInsets.only(left: 16.0, top: 8.0),
                            child: Text(
                              "• 1 Premium account\n• Cancel anytime\n• Subscribe or one-time payment",
                              style: TextStyle(
                                color: GlobalColors.textColor,
                                fontSize: 16,
                              ),
                            ),
                          ),
                          Padding(
                            padding: const EdgeInsets.only(
                              top: 16.0,
                              left: 16.0,
                              right: 16.0,
                              bottom: 8.0,
                            ),
                            child: Center(
                              child: Container(
                                width: double.infinity,
                                child: ElevatedButton(
                                  style: ElevatedButton.styleFrom(
                                    backgroundColor: GlobalColors.pinkColorLow,
                                  ),
                                  onPressed: () {},
                                  child: const Text(
                                    "Get Premium Individual",
                                    style: TextStyle(
                                      color: Colors.black,
                                      fontSize: 16,
                                      fontWeight: FontWeight.bold,
                                    ),
                                  ),
                                ),
                              ),
                            ),
                          ),
                          Padding(
                            padding:
                                const EdgeInsets.symmetric(horizontal: 16.0),
                            child: Center(
                              child: Container(
                                width: double.infinity,
                                child: ElevatedButton(
                                  style: ElevatedButton.styleFrom(
                                    backgroundColor: Colors.transparent,
                                    side: const BorderSide(
                                        color: Colors.white, width: 2),
                                  ),
                                  onPressed: () {},
                                  child: Text(
                                    "One-time payment",
                                    style: TextStyle(
                                      color: GlobalColors.textColor,
                                      fontSize: 16,
                                      fontWeight: FontWeight.bold,
                                    ),
                                  ),
                                ),
                              ),
                            ),
                          ),
                          Padding(
                            padding: const EdgeInsets.symmetric(vertical: 12.0),
                            child: Center(
                              child: Text(
                                "2.33\$ for 3 months, then 2.33\$ per month after. Offer only available if you haven't tried Premium before. Terms apply. Offer ends May 14, 2024",
                                style: TextStyle(
                                  color: GlobalColors.textColor,
                                ),
                                textAlign: TextAlign.center,
                              ),
                            ),
                          ),
                        ],
                      ),
                    ),
                  ],
                ),
              ),
              const SizedBox(
                height: 24,
              ),
              Container(
                decoration: BoxDecoration(
                  color: GlobalColors.cardColor,
                  borderRadius: BorderRadius.circular(10),
                ),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Container(
                      decoration: BoxDecoration(
                        color: GlobalColors.bluePurple,
                        borderRadius: const BorderRadius.only(
                          topLeft: Radius.circular(10.0),
                          bottomRight: Radius.circular(10.0),
                        ),
                      ),
                      child: Padding(
                        padding: const EdgeInsets.only(
                          top: 4.0,
                          bottom: 4.0,
                          left: 12.0,
                          right: 12.0,
                        ),
                        child: Text(
                          "1.16\$ for 3 months",
                          style: TextStyle(
                            color: GlobalColors.blackColor,
                            fontWeight: FontWeight.bold,
                            fontSize: 14,
                          ),
                        ),
                      ),
                    ),
                    Padding(
                      padding: const EdgeInsets.all(12.0),
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Padding(
                            padding: const EdgeInsets.only(bottom: 4.0),
                            child: Text(
                              'Premium',
                              style: TextStyle(
                                color: GlobalColors.textColor,
                                fontWeight: FontWeight.bold,
                                fontSize: 12,
                              ),
                            ),
                          ),
                          Text(
                            "Student",
                            style: TextStyle(
                              color: GlobalColors.bluePurple,
                              fontWeight: FontWeight.bold,
                              fontSize: 20,
                            ),
                          ),
                          Text(
                            "1.16\$ for 3 months",
                            style: TextStyle(
                              color: GlobalColors.textColor,
                              fontWeight: FontWeight.bold,
                              fontSize: 16,
                            ),
                          ),
                          Text(
                            "1.16\$/month after",
                            style: TextStyle(
                              color: GlobalColors.subTextColor,
                              fontSize: 14,
                            ),
                          ),
                          Divider(
                            color: GlobalColors.greyColor,
                          ),
                          Padding(
                            padding:
                                const EdgeInsets.only(left: 16.0, top: 8.0),
                            child: Text(
                              "• 1 verified Premium account\n• Discount for eligible students\n• Cancel anytime\n• Subscribe or one-time payment",
                              style: TextStyle(
                                color: GlobalColors.textColor,
                                fontSize: 16,
                              ),
                            ),
                          ),
                          Padding(
                            padding: const EdgeInsets.only(
                              top: 16.0,
                              left: 16.0,
                              right: 16.0,
                              bottom: 8.0,
                            ),
                            child: Center(
                              child: Container(
                                width: double.infinity,
                                child: ElevatedButton(
                                  style: ElevatedButton.styleFrom(
                                    backgroundColor: GlobalColors.bluePurple,
                                  ),
                                  onPressed: () {},
                                  child: const Text(
                                    "Get Premium Student",
                                    style: TextStyle(
                                      color: Colors.black,
                                      fontSize: 16,
                                      fontWeight: FontWeight.bold,
                                    ),
                                  ),
                                ),
                              ),
                            ),
                          ),
                          Padding(
                            padding:
                                const EdgeInsets.symmetric(horizontal: 16.0),
                            child: Center(
                              child: Container(
                                width: double.infinity,
                                child: ElevatedButton(
                                  style: ElevatedButton.styleFrom(
                                    backgroundColor: Colors.transparent,
                                    side: const BorderSide(
                                        color: Colors.white, width: 2),
                                  ),
                                  onPressed: () {},
                                  child: Text(
                                    "One-time payment",
                                    style: TextStyle(
                                      color: GlobalColors.textColor,
                                      fontSize: 16,
                                      fontWeight: FontWeight.bold,
                                    ),
                                  ),
                                ),
                              ),
                            ),
                          ),
                          Padding(
                            padding: const EdgeInsets.symmetric(vertical: 12.0),
                            child: Center(
                              child: Text(
                                "2.33\$ for 3 months, then 2.33\$ per month after. Offer only available only to students at accredited higher education institution and if you haven't tried Premium before. Terms apply. Offer ends May 14, 2024",
                                style: TextStyle(
                                  color: GlobalColors.textColor,
                                ),
                                textAlign: TextAlign.center,
                              ),
                            ),
                          ),
                        ],
                      ),
                    ),
                  ],
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }

  Widget showPremiumContent() {
    return Center(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
          Text('Bạn đã đăng ký gói premium!'),
          ElevatedButton(
            child: Text('Thay đổi gói'),
            onPressed: () {
              // Thực hiện hành động khi người dùng muốn thay đổi gói
            },
          ),
        ],
      ),
    );
  }
}
