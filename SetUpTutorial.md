# Introduction #

This is tutorial for setting up theme for BIG! caller ID. You will be able only to change resources of theme like images for buttons or font and color for caller field.

We are planing to add other features that will be themable, but that is going to be announced.

As for now there are two types of Themes that can be developed **ICS** or **regular** style . Both are avaiable via SVN or download section. Both have three sets of buttons for every phone state (IDLE, OFFHOOK, RINGING). Only difference is how buttons are aranged on screen.


# Instructions #

**1.** Download [regular](http://big-caller-id-theme-template.googlecode.com/files/StyleTemplate.rar) or the [ICS](http://big-caller-id-theme-template.googlecode.com/files/ICSStyleTemplate.rar) project, and open it In Eclipse or import it by [SVN](http://code.google.com/p/big-caller-id-theme-template/source/checkout), choose SDK 7 – the same as the version of BIG! caller ID.

**2.** In **Android Manifest** change x.x.x to your own package name.
Fields:  versionCode and versionName are up to you. You also have to change the x.x.x package name in src/ with Refractor -> Rename.

**3.** Add a font to the folder assets/fonts/  (or don’t add anything if you use a stock font).

**4.** In values/strings.xml change values **(not the name of a value!)**.

Follow these guidelines:

**info** – any text (this information will be displayed after turning on the app with loaded theme)

**app\_name** – as you wish

**style\_name** – the name of the theme (will be seen in Theme list within BIG! caller ID)

**package\_name** – name must be the same as in **Android Manifest**;

**caller\_filed\_font** – the path of the font placed assets/fonts/  . If you use stock Android font, enter “none” (without quotation marks). Path format:  fonts/font\_name , where font\_name is name of font used. This font is used in the caller’s name field;

**caller\_field\_number\_font** - same as above, but sets font for number field

**caller\_field\_text\_color** – the color of the caller’s name text

**caller\_field\_number\_text\_color** – the color of the caller’s number text

**caller\_field\_aligment** – caller's name aligment

**caller\_field\_number\_aligment** – caller's number aligment

Following fields sets shadow with textview function setShadowLayer [float, float, int)](http://developer.android.com/reference/android/widget/TextView.html#setShadowLayer(float,)

**caller\_name\_shadow\_dy**, **caller\_name\_shadow\_dx**, **caller\_name\_shadow\_radius**, **caller\_name\_shadow\_color**

**caller\_number\_shadow\_dy**, **caller\_number\_shadow\_dx**, **caller\_number\_shadow\_radius**, **caller\_number\_shadow\_color**

**caller\_field\_text\_size** – the size of the caller’s name text

**caller\_field\_number\_text\_size** – the size of the caller’s number text

**5.** **Positioning:**
You can set default position for fields: answer, dialpad, call, decline, end\_call, text, speaker, mute and caller\_field  by the means of variables (dimen) created in dimen.xml file. Position from a margin with dp value.

Variables have to retain this format:  [field\_name\_top|left|bottom|right]

**Example:** To position the “answer” button 20 pixels above the bottom margin and 0 pixels from the left margin you have to create variables: answer\_left valued 0dpi and answer\_bottom valued 20dpi.

**IMPROTANT: choose one vertical and one horizontal position only, e.g. if you create answer\_left don’t create answer\_right.**

caller\_field can be positioned only vertically.

If no variables (dimen) are created, the field will be positioned analogically to Minimalistic theme for BIG! caller ID.

You can preview position of buttons in layout incall.xml. It is important that after every change of a dimen name you have to apply proper changes in incall.xml file, so that the changes are visible.

**6.** Swap all the icons in drawable-hdpi folder. Every picture has a proper name which they must retain. Files are divided into on/off – clicked and not-clicked state. **Note that there two different tempaltes  with different drawables names**

**7.** **Do not** change anything in drawable folder.

**8.** If you want to add different graphics for more than 1 language, create new folder with a proper name, e.g. for Polish it should be:   drawable-pl-hdpi   . For more information about languages and locations visit: http://developer.android.com/guide/topics/resources/localization.html

**9.** Layout main.xml in the folder is at your disposal – you can do anything you want with it, and it won’t influence an imported theme. The same is layout incall.xml and dialpad.xml – they exist only for preview.

**10.** [Resources page](http://code.google.com/p/big-caller-id-theme-template/wiki/DrawableDescription) describes all the images of drawable-hdpi folder.

**11.** [Animation View Tutorial](http://code.google.com/p/big-caller-id-theme-template/wiki/AnimationTutorial) introduces to concept of animated view. Source code now have snowflakes algorithm form Christmas Theme, it should be understable with comments left inside java file.

**12.** After everything is done, just export your project like every other in eclipse and PUBLISH IT TO ANDROID MARKET. THEME IS YOUR PROPERYTY, so you can do whatever you want with it!

# Usage #

**1.** A theme is automatically imported to BIG! caller ID when a welcoming screen of this theme is displayed. It is deleted from the themes list only after uninstaling the theme on the device.

**2.** Theme can be **only** selected by full version users.

If you should encounter any problem, mail us to: support@wroclawstudio.com – we’ll definitely help you!