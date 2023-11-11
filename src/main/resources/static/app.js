document.getElementById('myForm').addEventListener('submit', function(e) {
    e.preventDefault();

    var accessToken = document.getElementById('accessToken').value;
    var postId = document.getElementById('postId').value;
    var blogName = document.getElementById('blogName').value;

    var data = {
        accessToken: accessToken,
        postId: postId,
        blogName: blogName
    };

    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/api/auto/regist', true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.onload = function() {
        if (this.status == 200) {
            $('#myModal').modal('show');

            document.getElementById('accessToken').value = '';
            document.getElementById('postId').value = '';
            document.getElementById('blogName').value = '';
        }
    };
    xhr.send(JSON.stringify(data));
});