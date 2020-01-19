import 'package:my_flutter/model/Banner.dart';

class BannerResp {
  int errorCode;
  String errorMsg;
  List<Banner> data;

  BannerResp(this.data);

  BannerResp.fromJson(Map<String, dynamic> json) {
    errorCode = json['errorCode'];
    errorMsg = json['errorMsg'];
    if (json['data'] != null) {
      data = (json['data'] as List).map((i)=>Banner.fromJson(i)).toList();
    }
  }
}
