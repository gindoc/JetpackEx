import 'package:flutter/material.dart';

class SecondPage extends StatelessWidget {
  String goodsId;

  SecondPage(this.goodsId);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('SecondPage'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              '从FirstPage接收的值：$goodsId',
              style: TextStyle(
                fontSize: 18,
                color: Colors.red,
              ),
            ),
            RaisedButton(
              onPressed: () {
                Navigator.pop(context, '这是从SecondPage返回的值:$goodsId');
              },
              child: Text('点击返回'),
            )
          ],
        ),
      ),
    );
  }
}
