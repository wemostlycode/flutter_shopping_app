import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter_shopping_app/src/app.dart';
import 'package:flutter_shopping_app/src/bloc/bloc.dart';
import 'package:flutter_shopping_app/src/di/app_injector.dart';

void main() {
  runZonedGuarded(
    () {
      WidgetsFlutterBinding.ensureInitialized();
      AppInjector.create();
      Bloc.observer = MyBlocObserver();
      runApp(App());
    },
    (error, stack) {
      print(error);
    },
    zoneSpecification: ZoneSpecification(),
  );
}
