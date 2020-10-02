import 'package:get_it/get_it.dart';

GetIt _injector = GetIt.I;

class AppInjector {
  AppInjector._internal();

  static T get<T>({String instanceName, dynamic param1, dynamic param2}) =>
      _injector<T>(instanceName: instanceName, param1: param1, param2: param2);

  static void create() {
    _initCubits();
    _initRepos();
    _initNotifiers();
  }

  static _initRepos() {
    // _injector.registerLazySingleton(() => PreferenceRepository());
    // _injector.registerLazySingleton(() => ApiRepository());
  }

  static _initCubits() {}

  static void _initNotifiers() {}

  static close() {
    _injector.reset();
  }
}
