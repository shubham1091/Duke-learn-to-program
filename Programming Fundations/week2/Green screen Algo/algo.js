// write your code here
var fgImage = new SimpleImage("drewRobert.png");
var bgImage = new SimpleImage("dinos.png");
var output = new SimpleImage(fgImage.getWidth(),fgImage.getHeight());

for (var pixel of fgImage.values()){
    //Look at current Pixel and if it is green
    var x = pixel.getX();
    var y = pixel.getY();
    if(pixel.getGreen()>pixel.getRed()+pixel.getBlue()){
        //Look at same position in bgImage
        var bgPixel = bgImage.getPixel(x, y);
        //and set output's corresponding pixel to bgImage's pixel
        output.setPixel(x, y, bgPixel);
    }else{
        output.setPixel(x,y,pixel)
    }
}
print(output)