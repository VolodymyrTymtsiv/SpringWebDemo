$("#cover-file").change(function (){
    var fd = new FormData();
    var file = $('#cover-file')[0].files[0];

    if(file === undefined){
        return;
    }

    fd.append('coverFile', file);
    $.ajax({
        url: '/ILibrary/book-cover-files/upload',
        type: 'post',
        data: fd,
        contentType: false,
        processData: false,
        success: function (coverId) {
            $("#book-cover")
                .attr("src", "/ILibrary/book-cover-files/download/" + coverId);
            $("#cover-id").val(coverId)
        },
    });
});

