import 'package:fluro/fluro.dart';
import 'package:my_flutter/pages/fluro/fluro_second_page.dart';

Handler secondPageHandler = Handler(handlerFunc: (context, params) {
  String goodsId = params['goodsId'].first;
  return SecondPage(goodsId);
});
