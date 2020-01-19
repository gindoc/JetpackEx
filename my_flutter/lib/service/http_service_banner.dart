import 'dart:async';

import 'package:dio/dio.dart';

Future requestBanner(url) async {
  try {
    Response response;
    Dio dio = new Dio();
    response = await dio.get(url);
    if (response.statusCode == 200) {
      return response;
    } else {
      throw Exception("请求Banner图失败");
    }
  } catch (e) {
    return print('error:::${e}');
  }
}
