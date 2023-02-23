
if (document.images) {
  image1on = new Image();
  image1on.src = "../menuFiles//nav-downbullet.gif";
  image2on = new Image();
  image2on.src = "../menuFiles//nav-downbullet.gif";
  image3on = new Image();
  image3on.src = "../menuFiles//nav-downbullet.gif";
  image4on = new Image();
  image4on.src = "../menuFiles//nav-downbullet.gif";
  image5on = new Image();
  image5on.src = "../menuFiles//nav-downbullet.gif";
  image6on = new Image();
  image6on.src = "../menuFiles//nav-downbullet.gif";
  image7on = new Image();
  image7on.src = "../menuFiles//nav-downbullet.gif";
  
  
  image1off = new Image();
  image1off.src = "../menuFiles//nav-bullet.gif";
  image2off = new Image();
  image2off.src = "../menuFiles//nav-bullet.gif";
  image3off = new Image();
  image3off.src = "../menuFiles//nav-bullet.gif";
  image4off = new Image();
  image4off.src = "../menuFiles//nav-bullet.gif";
  image5off = new Image();
  image5off.src = "../menuFiles//nav-bullet.gif";
  image6off = new Image();
  image6off.src = "../menuFiles//nav-bullet.gif";
  image7off = new Image();
  image7off.src = "../menuFiles//nav-bullet.gif";
}

function turnOn(imageName) {
  if (document.images) {
    document[imageName].src = eval(imageName + "on.src");
  }
}

function turnOff(imageName) {
  if (document.images) {
    document[imageName].src = eval(imageName + "off.src");
  }
}
