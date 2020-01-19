class Banner{
  String desc;
  int id;
  String imagePath;
  int isVisible;
  int order;
  String title;
  int type;
  String url;

  Banner.fromJson(Map<String, dynamic> parsedJson){
    desc = parsedJson['desc'];
    id = parsedJson['id'];
    imagePath = parsedJson['imagePath'];
    isVisible = parsedJson['isVisible'];
    order = parsedJson['order'];
    title = parsedJson['title'];
    type = parsedJson['type'];
    url = parsedJson['url'];
  }
}