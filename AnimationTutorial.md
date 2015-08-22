# Animation Tutorial #

Since BIG! caller ID v1.7.5 we have introduced animated background. It is displayed over caller image and below every button in caller screen.

# How it works? #
BIG! caller ID is importing custom view class from style application. Custom view has to be called AnimatedView in order it to be properly imported.
After creating view in BIG! caller ID it is displayed and continusly invalidated in background thread in order to be refreshed.

# Getting started #

# Methods overview #
Animated View is inherits from View parent class. It has floowing methods: onMeasure, chooseDimension, onDraw and constructor AnimationView.
Methods onMesure and chooseDimension are called to get view metrics where it will be drawn. As we use fill\_parent parameter for width and heigth it will retrun full screen.
It is advised not to change those methods.

Method onDraw is used to draw animation on screen. This is the place where algorithm for you animation has to be writen.

Constructor for custom view is called only with one parameter - context. If you create other constructors with other parameters they will not be called during importing to BIG! caller ID resulting in view malfunction

# Using bitmaps #
If you are willing to use bitmaps in your animation, you have to be aware that code in AnimationView is executed in other application, therefor local methods like getDrawable will not return drawable that you want.
In order to get drawable you need you have to use following code:

String packageName = "x.x.x";
try{
> Resources res = getContext().getPackageManager().getResourcesForApplication(packageName);
> Bitmap Icon = BitmapFactory.decodeResource(res,	R.drawable.icon);
}catch (NameNotFoundException e) {
> e.printStackTrace();
}

Note that you have to define package name (x.x.x) of your application from which resources will be imported, this is because code is not executed within style application.

NOTE: Wro Claw Studio is not responsible for code created by developers and executed within BIG! caller ID application.