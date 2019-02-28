<h2>READ ME</h2>
This streaming platform app can be used for e-book or to play musics in the albums. <br>
The app feeds data that comes from the API that I have created for this purpose.<br>

<h4>The API contains(list of albums):</h4> https://github.com/Dannykl/AlbumAPI
	
	album name or book name, genres name, released date, album or book poster, 
	artist/s name/s or author/s name/s ,name of song or chapter, location of music or chapter (Uri)




```
{
	"albums": [
	{
		"album_name": "album name",
		"genres_name": "genres",
		"released_date": "2001-01-01",
		"album_poster": "http://././x.jpg",
		"artists": [
		{
			"r_name": "artist name1"
		},
		{
			"r_name": "artist name2"
		}],
		"song_details": [
		{
			"songName": "title",
			"song_location": "http://www././x.mp3"
		},
		{
			"songName": "song name",
			"song_location": "http://www././y.mp3"
		}]
	},

	{

	}],
}
```




<h4>In addition:</h4>

Retrofit is used to handle API<br>
Glide is used to handle images from uri
Reactivex


