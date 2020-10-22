
# Outline Shadow of View: Example 


we can take the advantage of `outline.setConvexPath(path);` in elevation shadow since it will work fine as an outline for the elevation shadow around the path.


I attached an image to make it even more clear in which:

**First image**: ImageView with [Image with shadow]

    android:outlineProvider="bounds"  
    android:elevation="4dp"

**Second image**: CustomImageView with `canvas.clipPath(getShapePath())` in `onDraw()` method [we will get the clipped image but shadow still show as a rectangle bound]

    android:outlineProvider="bounds"  
    android:elevation="4dp"

**Third image**: CustomImageView with `canvas.clipPath(getShapePath())` in `onDraw()` method and `outline?.setConvexPath(getShapePath())` in `ViewOutlineProvider`. [we will get the clipped image and shadow will be aligned with the clipped image]

    android:outlineProvider="bounds" // this will have no effect since it will be override by setConvexPath  
    android:elevation="4dp"


## Screen shots

![Screenshot](https://user-images.githubusercontent.com/6468283/96850171-22f25200-1474-11eb-9068-54b5c90b4b4c.png)
