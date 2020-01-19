import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:my_flutter/model/Banner.dart' as DataBanner;

import '../model/BannerResp.dart';
import '../service/http_service_banner.dart';

class BannerListPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _BannerListPageState();
  }
}

class _BannerListPageState extends State<BannerListPage> {
  BannerResp resp = BannerResp([]);

  var scrollController = ScrollController();

  @override
  void initState() {
    super.initState();
    getBanner();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text("网络请求"),
        ),
        body: _buildWidget(context),
      ),
    );
  }

  void getBanner() async {
    var url = "https://www.wanandroid.com/banner/json";
    await requestBanner(url).then((value) {
      var data = json.decode(value.toString());
      print('Banner列表数据Json格式:::' + data.toString());
      setState(() {
        resp = BannerResp.fromJson(data);
      });
    });
  }

  Widget _buildWidget(BuildContext context) {
    if (resp.data.length > 0) {
      return ListView.builder(
          controller: scrollController,
          itemCount: resp.data.length,
          itemBuilder: (context, index) {
            return _listWidget(resp.data, index);
          });
    } else {
      return Container();
    }
  }

  Widget _listWidget(List<DataBanner.Banner> data, int index) {
    return Container(
      decoration: BoxDecoration(
          color: Colors.white,
          border: Border(
            bottom: BorderSide(width: 1, color: Colors.black12),
          )),
      child: Column(
        children: <Widget>[
          _bannerImage(data, index),
          SizedBox(
            width: 10,
          ),
          _bannerText(data, index),
        ],
      ),
    );
  }

  _bannerImage(List<DataBanner.Banner> data, int index) {
    return Container(
      width: double.infinity,
      height: 150,
      child: Image.network(
        data[index].imagePath,
        fit: BoxFit.fill,
      ),
    );
  }

  _bannerText(List<DataBanner.Banner> data, int index) {
    return Text(
      data[index].desc,
      maxLines: 2,
      overflow: TextOverflow.ellipsis,
      style: TextStyle(fontSize: 18),
    );
  }
}
