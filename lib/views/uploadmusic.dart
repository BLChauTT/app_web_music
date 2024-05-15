import 'dart:io';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:path_provider/path_provider.dart';
import 'package:spocify/utils/global.colors.dart';
import 'package:file_picker/file_picker.dart';

class UploadPage extends StatefulWidget {
  @override
  _UploadPageState createState() => _UploadPageState();
}

class _UploadPageState extends State<UploadPage> {

  final TextEditingController songnameController = TextEditingController();
  final TextEditingController authorController = TextEditingController();
  final TextEditingController descriptionController = TextEditingController();
  final TextEditingController tagController = TextEditingController();

  void uploadMusic() async {
    if (songnameController.text.isEmpty || authorController.text.isEmpty) {
      showDialog(
        context: context,
        builder: (BuildContext context) {
          return AlertDialog(
            title: Text('Error'),
            content: Text('Song name or artist cannot be empty'),
            actions: <Widget>[
              TextButton(
                child: Text('OK'),
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

    FilePickerResult? result = await FilePicker.platform.pickFiles(
      type: FileType.custom,
      allowedExtensions: ['mp3'],
    );

    if (result != null) {
      PlatformFile songFile = result.files.first;

      print('Picked song: ${songFile.name}');

      // Lưu tệp nhạc vào thư mục tạm thời
      // Directory tempDir = await getTemporaryDirectory();
      // File tempFile = File('${tempDir.path}/${songFile.name}');
      // if (songFile.bytes != null) {
      //   await tempFile.writeAsBytes(songFile.bytes!);
      // } else {
      //   print('Error: song file bytes is null');
      // }
      //
      // print('Saved song to: ${tempFile.path}');
      try {
        // Lấy thư mục lưu trữ tài liệu của ứng dụng
        Directory directory = await getApplicationDocumentsDirectory();
        String filePath = '${directory.path}/${songFile.name}';

        // Tạo tệp mới và ghi nội dung vào đó
        File file = File(filePath);
        await file.writeAsBytes(songFile.bytes!);

        print('Đã lưu file vào: $filePath');
      } catch (e) {
        print('Lỗi khi lưu file: $e');
      }

    } else {
      // User canceled the picker
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
          ),
          body: Scaffold(
            backgroundColor: Colors.transparent,
            body: Column(
              children: [
                Container(
                  height: 55,
                  padding: const EdgeInsets.only(
                    top: 3,
                    left: 15,
                  ),
                  decoration: BoxDecoration(
                    color: Colors.transparent,
                    borderRadius: BorderRadius.circular(6),
                    boxShadow: const [
                      BoxShadow(
                        color: Colors.transparent,
                        blurRadius: 7,
                      ),
                    ],
                  ),
                  child: TextFormField(
                    keyboardType: TextInputType.text,
                    controller: songnameController,
                    obscureText: false,
                    style: TextStyle(color: GlobalColors.textColor),
                    decoration: InputDecoration(
                      hintText: "Song's name",
                      hintStyle: TextStyle(
                        color: GlobalColors.textColor.withOpacity(0.5),
                        height: 1.0,
                      ),
                      contentPadding: const EdgeInsets.all(8.0),
                      border: InputBorder.none,
                    ),
                  ),
                ),
                Container(
                  height: 55,
                  padding: const EdgeInsets.only(
                    top: 3,
                    left: 15,
                  ),
                  decoration: BoxDecoration(
                    color: Colors.transparent,
                    borderRadius: BorderRadius.circular(6),
                    boxShadow: const [
                      BoxShadow(
                        color: Colors.transparent,
                        blurRadius: 7,
                      ),
                    ],
                  ),
                  child: TextFormField(
                    keyboardType: TextInputType.text,
                    controller: authorController,
                    obscureText: false,
                    style: TextStyle(color: GlobalColors.textColor),
                    decoration: InputDecoration(
                      hintText: "Author",
                      hintStyle: TextStyle(
                        color: GlobalColors.textColor.withOpacity(0.5),
                        height: 1.0,
                      ),
                      contentPadding: const EdgeInsets.all(8.0),
                      border: InputBorder.none,
                    ),
                  ),
                ),
                Container(
                  height: 55,
                  padding: const EdgeInsets.only(
                    top: 3,
                    left: 15,
                  ),
                  decoration: BoxDecoration(
                    color: Colors.transparent,
                    borderRadius: BorderRadius.circular(6),
                    boxShadow: const [
                      BoxShadow(
                        color: Colors.transparent,
                        blurRadius: 7,
                      ),
                    ],
                  ),
                  child: TextFormField(
                    keyboardType: TextInputType.text,
                    controller: descriptionController,
                    obscureText: false,
                    style: TextStyle(color: GlobalColors.textColor),
                    decoration: InputDecoration(
                      hintText: "Description",
                      hintStyle: TextStyle(
                        color: GlobalColors.textColor.withOpacity(0.5),
                        height: 1.0,
                      ),
                      contentPadding: const EdgeInsets.all(8.0),
                      border: InputBorder.none,
                    ),
                  ),
                ),
                Container(
                  height: 55,
                  padding: const EdgeInsets.only(
                    top: 3,
                    left: 15,
                  ),
                  decoration: BoxDecoration(
                    color: Colors.transparent,
                    borderRadius: BorderRadius.circular(6),
                    boxShadow: const [
                      BoxShadow(
                        color: Colors.transparent,
                        blurRadius: 7,
                      ),
                    ],
                  ),
                  child: TextFormField(
                    keyboardType: TextInputType.text,
                    controller: tagController,
                    obscureText: false,
                    style: TextStyle(color: GlobalColors.textColor),
                    decoration: InputDecoration(
                      hintText: "Tag",
                      hintStyle: TextStyle(
                        color: GlobalColors.textColor.withOpacity(0.5),
                        height: 1.0,
                      ),
                      contentPadding: const EdgeInsets.all(8.0),
                      border: InputBorder.none,
                    ),
                  ),
                ),
                ElevatedButton(
                  child: Text('Upload'),
                  onPressed: uploadMusic,
                ),
              ],
            ),
          ),
        ),
      );
    }
}
