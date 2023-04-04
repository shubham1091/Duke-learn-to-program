var image = new SimpleImage("duke_blue_devil.png");
for(var pixel of image.values()){
    if(pixel.getBlue()>pixel.getRed()){
        pixel.setRed(255)
        pixel.setGreen(255)
        pixel.setBlue(0)
    }
}
print(image)