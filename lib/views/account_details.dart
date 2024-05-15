import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter/widgets.dart';
import 'package:spocify/app/globals.dart';
import 'package:spocify/entities/account_details.dart';
import 'package:spocify/utils/global.colors.dart';
import 'package:spocify/views/home.dart';
import 'package:spocify/views/widgets/number.form.global.dart';
import 'package:spocify/views/widgets/text.form.global.dart';

import '../Helpers/account_detail_helpers.dart';

class AccountDetailsPage extends StatefulWidget {
  @override
  _AccountDetailsPageState createState() => _AccountDetailsPageState();
}

class _AccountDetailsPageState extends State<AccountDetailsPage> {
  var dbAccountDetails = AccountDetailDBHelper();

  final TextEditingController firstName = TextEditingController();
  final TextEditingController lastName = TextEditingController();
  final TextEditingController email = TextEditingController();
  final TextEditingController age = TextEditingController();
  final TextEditingController country = TextEditingController();
  dynamic current_id;
  @override
  void initState() {
    super.initState();
    fetchAccountDetails();
  }

  Future<void> fetchAccountDetails() async {
    var accountDetails = await dbAccountDetails.findByUsername(usernameinlogin);

    // print(usernameinlogin);
    // print("id hiện tại: ${accountDetails?.id!}");
    // print("First name: ${accountDetails?.username!}");
    // print("Last name: ${accountDetails?.firstname!}");
    // print("Last name: ${accountDetails?.lastname!}");
    // print("Email: ${accountDetails?.email!}");
    // print("Age: ${accountDetails?.age!}");
    // print("Country: ${accountDetails?.country!}");

    if (accountDetails != null) {
      setState(() {
        firstName.text = accountDetails.firstname!;
        lastName.text = accountDetails.lastname!;
        email.text = accountDetails.email!;
        age.text = accountDetails.age!;
        country.text = accountDetails.country!;
      });
    }
  }

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
              padding: const EdgeInsets.only(right: 52.0),
              child: Text(
                "Account Details",
                style: TextStyle(
                  color: GlobalColors.textColor.withOpacity(0.9),
                  fontWeight: FontWeight.bold,
                  fontSize: 18.0,
                ),
              ),
            ),
          ),
        ),
        body: _accountDetailsPage(),
      ),
    );
  }

  Widget _accountDetailsPage() {
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
                  controller: firstName,
                  text: "First Name",
                  obscure: false,
                  textInputType: TextInputType.text,
                ),
                const SizedBox(
                  height: 16.0,
                ),
                TextFormGlobal(
                  controller: lastName,
                  text: "Last Name",
                  obscure: false,
                  textInputType: TextInputType.text,
                ),
                const SizedBox(
                  height: 16.0,
                ),
                TextFormGlobal(
                  controller: email,
                  text: "Email",
                  obscure: false,
                  textInputType: TextInputType.emailAddress,
                ),
                const SizedBox(
                  height: 16.0,
                ),
                NumberFormGlobal(
                  controller: age,
                  text: "Age",
                  obscure: false,
                  textInputType: TextInputType.number,
                  inputFormatters: <TextInputFormatter>[
                    FilteringTextInputFormatter.digitsOnly
                  ],
                  validator: (value) {
                    final isDigitsOnly = int.tryParse(value);
                    return isDigitsOnly == null ? 'Input needs to be digits only' : null;
                  },
                ),
                const SizedBox(
                  height: 16.0,
                ),
                TextFormGlobal(
                  controller: country,
                  text: "Country",
                  obscure: false,
                  textInputType: TextInputType.emailAddress,
                ),
                const SizedBox(
                  height: 32.0,
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
                      "Save",
                      style: TextStyle(
                        color: Colors.white,
                        fontWeight: FontWeight.w600,
                      ),
                    ),
                  ),
                  onTap: (){
                    update();
                  },
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
                  onTap: (){},
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }

  void update() async {
    var accountDetails = await dbAccountDetails.findByUsername(usernameinlogin);

    print(usernameinlogin);
    current_id=accountDetails?.id!;
    print("giá trị của curent id : ${current_id}");
    var accDetail = AccountDetail(
      id: current_id,
      username: usernameinlogin,
      firstname: firstName.text.toString() ?? "0",
      lastname: lastName.text.toString() ?? "0",
      email: email.text.toString() ?? "0",
      age: age.text.toString() ?? "0",
      country: country.text.toString() ?? "0",
    );
    // print('===================================');
    // print("id hiện tại: ${current_id}");
    // print("username hiện tại: ${accDetail.username}");
    // print("First name: ${accDetail.firstname}");
    // print("Last name: ${accDetail.lastname}");
    // print("Email ${accDetail.email}");
    // print("Tuổi: ${accDetail.age}");
    // print("Nước: ${accDetail.country}");
    // print('===================================');

    print(await dbAccountDetails.deleteAndCreateNewAccountDetail(accDetail));

    Navigator.pushReplacement(
      context,
      MaterialPageRoute(
        builder: (context) => AccountDetailsPage(),
      ),
    );
  }

}
