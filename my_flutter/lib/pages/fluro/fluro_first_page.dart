import 'package:fluro/fluro.dart';
import 'package:flutter/material.dart';
import 'package:my_flutter/pages/fluro/fluro_application.dart';
import 'package:my_flutter/pages/fluro/fluro_routes.dart';

void main() => runApp(FluroDemo());

class FluroDemo extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final Router router = Router();
    Routers.configureRoutes(router);
    FluroApplication.router = router;

    return MaterialApp(
      title: 'Fluro路由导航示例',
      debugShowCheckedModeBanner: false,
      // 生成路由的回调函数，当导航到目标路由时，会使用回调函数来生成界面
      onGenerateRoute: FluroApplication.router.generator,
      home: FluroFirstPage(),
    );
  }
}

class FluroFirstPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Fluro路由导航示例_FistPage'),
      ),
      body: Builder(
        builder: (context) => Center(
          child: RaisedButton(
            onPressed: () {
              _navigate2secondPage(context);
            },
            child: Text('打开第二个页面'),
          ),
        ),
      ),
    );
  }

  _navigate2secondPage(BuildContext context) {
    String goodsId = "firstPage_GoodsId";
    FluroApplication.router
        .navigateTo(context, '${Routers.second_page}?goodsId=$goodsId',
            transition: TransitionType.fadeIn)
        .then((result) {
      if (result != null) {
        SnackBar bar = SnackBar(
          content: Text('$result'),
        );
        Scaffold.of(context).showSnackBar(bar);
      }
    });
  }
}
