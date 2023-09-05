## Setup

The project requires JDK 17, which can be set
via `Settings > Build, Execution, Deployment > Build Tools > Gradle > Gradle JDK`.

## App

This app uses api provided from https://jsonplaceholder.typicode.com/ using `albums`,`photos`
& `users` endpoints.
The api returns dummy text, however the reason to chose the api - is to demonstrate optimal
performant solution
when integrating real world rest APIs with client.

The api

- `/albums` return 100 albums
- `/photos`    returns 5000 photos
- `/users` returns 10 users
- GET `/albums/2/`
- GET `/photos?albumId=2`
- `/albums?_start=1&_limit=10` Supports offset start and limit pagination's

The app shows list of `albums` - aggregating data like `album title`, `username`, `photo name`
and `photo image` from above endpoints.
Each unique album id has 50 photos, the app show the photo title and image url of first photo of
each album in the list.

| Albums list success          | Album page error/retry state            | Failed to get error                | RTL                               |
|------------------------------|-----------------------------------------|------------------------------------|-----------------------------------|
| ![](images/paged_succes.png) | ![](images/paged_error_retry_state.png) | ![](images/failed_to_get_user.png) | ![](images/paged_success_rtl.png) |


## Feature

## Demo

![](images/demo.gif)

- This app shows list of `photo albums` - with album title, username, photo name and photo image.
- The app only shows the first photo of each album.
- The implementation is based on the pagination support via the api endpoints with `start`
  and `limit` offsets. I choose pagination solution to ensures network and system resources are used
  efficiently.
- As data set is large and involved calling multiple endpoints. The `/users` call is made once and
  then `/albums` and `/photo` is called as the user scrolls.
- To fetch first photo of each album I rely on limit offset - this ensures less data is transferred
  when fetching photos of each album `/photos?album{id}&_limit={1}`.

## Tests

- AlbumsRepositoryTest
- PhotosRepositoryTest
- UsersRepositoryTest
- GetAlbumAndPhotoUseCaseTest
- GetUserInfoUseCaseTest
- AlbumDetailPagingSourceTest
- AlbumDetailUIModelMapperTest
- AlbumsViewModelTest
