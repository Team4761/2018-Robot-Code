mkdir -p ~/wpilib/user/java/lib
echo "version=current\nteam-number=4761" > ~/wpilib/wpilib.properties
wget https://gist.githubusercontent.com/simon-andrews/0127707da461e543cb76/raw/dl_wpilibjars.py
python3 dl_wpilibjars.py --dest ~/wpilib/java/current
rm dl_wpilibjars.py
