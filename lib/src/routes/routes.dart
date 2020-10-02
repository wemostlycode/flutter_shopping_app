import 'package:auto_route/auto_route_annotations.dart';
import 'package:flutter_shopping_app/src/ui/screens/splash_screen.dart';

@CupertinoAutoRouter(
  routes: [
    CupertinoRoute(page: SplashScreen, initial: true),
    // CupertinoRoute(page: HomeScreen),
  ],
  routesClassName: "Routes",
)
class $AppRouter {}
