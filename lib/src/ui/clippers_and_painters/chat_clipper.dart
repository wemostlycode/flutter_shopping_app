import 'dart:ui';

import 'package:flutter/material.dart';

class ChatClipper extends CustomClipper<Path> {
  final bool isLeft;

  ChatClipper(this.isLeft);

  @override
  Path getClip(Size size) {
    if (isLeft) {
      Path path = Path()
//      ..moveTo(0, size.height)
        ..lineTo(size.width, 0)
        ..lineTo(size.width, size.height - 14)
        ..arcToPoint(Offset(size.width - 4, size.height - 10),
            radius: Radius.circular(10))
        ..lineTo(10, size.height - 10)
        ..lineTo(0, size.height)
        ..close();

      return path;
    } else {
      Path path = Path()
//      ..moveTo(0, size.height)
        ..lineTo(size.width, 0)
        ..lineTo(size.width, size.height)
        ..lineTo(size.width - 10, size.height - 10)
        ..lineTo(4, size.height - 10)
        ..arcToPoint(Offset(0, size.height - 14), radius: Radius.circular(10))
        ..lineTo(0, 0)
        ..close();

      return path;
    }
  }

  @override
  bool shouldReclip(CustomClipper<Path> oldClipper) => true;
}
