// write your code here
function redHigh(name){
    for (var pixel of name.values()){
        pixel.setRed(255)
    }
}
function removeRed(name){
    for(var pixel of name.values()){
        if(pixel.getRed()>0){
            pixel.setRed(0)
        }
    }
}
function under70(name){
    for(var pixel of name.values()){
        if(pixel.getRed()>70){
            pixel.setRed(70)
        }
    }
}
function bottom(name,h){
    var height = name.getHeight()
    var newHeight = height-h
    for (var pixel of name.values()){
        if(pixel.getY()>=newHeight){
            pixel.setRed(0)
            pixel.setGreen(0)
            pixel.setBlue(0)
        }
    }
}
function leftCornor(name,x,y){
    for (var pixel of name.values()){
        if(pixel.getX()<x){
            if(pixel.getY()<y){
                pixel.setRed(0)
                pixel.setGreen(255)
                pixel.setBlue(0)
            }
        }
    }
}
function topRightCorner(cornerWidth, cornerHeight, someImage, red, green, blue) {
    
    var w = someImage.getWidth()-cornerWidth
    for(var pixel of someImage.values()){
        if(pixel.getX()>=w){
            if(pixel.getY()<cornerHeight){
                pixel.setRed(red)
                pixel.setGreen(green)
                pixel.setBlue(blue)
            }
        }
    }
    return someImage
}
function changeRed(width, height) {
    var picture = new SimpleImage(width, height);
    var red = 0;

    for(var pixel of picture.values()){
        pixel.setRed(red);
        pixel.setGreen(100);
        pixel.setBlue(200);
        if (pixel.getRed() < 255) {
            red = red + 1;
        }
        if (pixel.getRed() == 255) {
            red = 0;
        }
    }
    return picture;
}


