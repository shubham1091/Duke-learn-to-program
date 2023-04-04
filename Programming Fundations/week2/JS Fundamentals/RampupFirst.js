function phrase3words(ph1,ph2,ph3){
    var ans = ph1 + " "+ph2+" "+ph3;
    return ans;
}
function numpix(somefile){
    var height = somefile.getHeight();
    var width = somefile.getWidth();
    return height*width;
}
function parameter(somefile){
    var height = somefile.getHeight();
    var width = somefile.getWidth();
    return 2*(height*width);
}
function printPixel(nameImage, xpos, ypos) {
    var pixle = nameImage.getPixel(xpos,ypos);
    var r = pixle.getRed()
    var g = pixle.getGreen()
    var b = pixle.getBlue()
    print("red is",r)
    print("green is",g)
    print("blue is",b)
}
function sumPixel(nameOfImage, xpos, ypos) {
    var pic = new SimpleImage(nameOfImage);
    var pixel = pic.getPixel(xpos,ypos);
    var r = pixel.getRed()
    var g = pixel.getGreen()
    var b = pixel.getBlue()
    return r+g+b
}

var one = phrase3words("Hello",'my',"friend");
print(one)
var pic = new SimpleImage("chapel.png");
var drew = new SimpleImage("drewgreen.png")
printPixel(drew,250,500);
print(numpix(pic))
var answer = sumPixel("drewgreen.png", 250, 500);
print(answer);
