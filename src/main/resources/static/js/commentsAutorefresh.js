$(document).ready(() => {
    let table = $('#itemComments');
    let latestCommentId = table.data('latest-comment-id');
    let itemId = table.data('item-id');

    setInterval(() => {
        $.get("/comments", { itemId: itemId, latestCommentId: latestCommentId }, data => {
            data = $.parseJSON(data);
            for (let comment of data) {
                const commentData = {
                    author: comment['author'],
                    comment: comment['comment'],
                };

                let row = '<tr>';
                row += '<td><h4>' + commentData['author'] + '</h4></td>'
                row += '<td><p>' + commentData['comment'] + '</p></td>'
                row += '</tr>';

                latestCommentId = comment['id'];
                table.find('tbody').append(row);
            }
        });
    }, 3000);
});
