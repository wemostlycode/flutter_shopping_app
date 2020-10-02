import 'package:flutter/material.dart';
import 'package:flutter_shopping_app/src/res/res.dart';
import 'package:flutter_shopping_app/src/ui/screens/splash_screen.dart';

import 'di/app_injector.dart';

class App extends StatefulWidget {
  @override
  _AppState createState() => _AppState();
}

class _AppState extends State<App> {
  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: AppStringsConstants.appName,
      theme: AppTheme.appTheme(),
      onGenerateRoute: AppRouter(),
      home: SplashScreen(),
    );
  }

  @override
  void dispose() {
    super.dispose();
    AppInjector.close();
  }
}
