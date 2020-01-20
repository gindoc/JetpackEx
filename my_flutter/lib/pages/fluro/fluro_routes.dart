import 'package:fluro/fluro.dart';
import 'package:flutter/widgets.dart';
import 'package:my_flutter/pages/fluro/fluro_handlers.dart';

class Routers {
  static String root = "/"; // 根路径，指向materialApp的home

  static String second_page = "/secondPage";

  static void configureRoutes(Router router) {
    router.notFoundHandler = Handler(handlerFunc:
        (BuildContext context, Map<String, List<String>> parameters) {
      print('error:::router没有找到');
      return Text('Unknown route', textDirection: TextDirection.ltr);
    });

    router.define(second_page, handler: secondPageHandler);
  }
}
