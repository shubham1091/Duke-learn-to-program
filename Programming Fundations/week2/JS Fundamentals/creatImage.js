// write your code here
function change(img,r,g,b){
    for (var i of im.values()){
    i.setRed(r)
    i.setGreen(g)
    i.setBlue(b)
}
}
var im = new SimpleImage(200,200);
change(im,255,255,0)
// change(im,255,0,255)
print(im)