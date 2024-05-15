class Category {
  late final String title;
  late final String coverUrl;

  Category({
    required this.title,
    required this.coverUrl,
  });

  static List<Category> categories = [
    Category(
      title: "Music",
      coverUrl: "assets/images/category/music.jpg",
    ),
    Category(
      title: "Chill",
      coverUrl: "assets/images/category/chill.jpg",
    ),
    Category(
      title: "Electronic",
      coverUrl: "assets/images/category/electronic.jpg",
    ),
    Category(
      title: "Hip-Hop",
      coverUrl: "assets/images/category/hiphop.jpg",
    ),
    Category(
      title: "Indie",
      coverUrl: "assets/images/category/indie.jpg",
    ),
    Category(
      title: "K-Pop",
      coverUrl: "assets/images/category/kpop.jpg",
    ),
    Category(
      title: "Love",
      coverUrl: "assets/images/category/lovew.jpg",
    ),
    Category(
      title: "Mood",
      coverUrl: "assets/images/category/mood.jpg",
    ),
    Category(
      title: "Party",
      coverUrl: "assets/images/category/party.jpeg",
    ),
    Category(
      title: "Pop",
      coverUrl: "assets/images/category/pop.jpg",
    ),
    Category(
      title: "R&B",
      coverUrl: "assets/images/category/RandB.jpeg",
    ),
    Category(
      title: "Sleep",
      coverUrl: "assets/images/category/sleep.jpg",
    ),
    Category(
      title: "Vietnamese Music",
      coverUrl: "assets/images/category/vietnam.jpg",
    ),
    Category(
      title: "Workout",
      coverUrl: "assets/images/category/workout.jpeg",
    ),
  ];
}
