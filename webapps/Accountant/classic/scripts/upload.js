var progress;
			function upload () {
				// avoid concurrent processing
				if (progress) return;
				var uploadform = document.getElementById('uploadform'),
					time = new Date().getTime();
				uploadform.action = 'UploadFile?time=' + time;
				uploadform.target = 'target-frame';
				uploadform.submit();
				startProgressbar(time);
			}
			function startProgressbar (startTime) {
				// display progress bar
				$('.progress-bar').css('display', 'block');
				// start timer
				progress = setInterval(function () {
					// ask progress
					$.ajax({
						type: "GET",
						url: "UploadFile",
						data: {time: startTime},
						success: function (data, textStatus,jqXHR ) {
							// get progress from response data
							var d = eval('(' + data + ')'),
								uploadprogress = parseInt(d.progress[startTime]);
							// change progress width
							$('.progress').css('width', uploadprogress+'px');
							if (uploadprogress == 100) { // upload finished
								// stop timer
								clearInterval(progress);
								setTimeout(function () {
									// hide progress bar
									$('.progress-bar').css('display', '');
									$('.progress').css('width', '');
									// clear timer variable
									progress = null;
								}, 1000);
							}
						}
					})
				}, 1000);
			}


function upload2(fileInput) {
    var formData = new FormData();
    formData.append('file', fileInput.files[0]);
    var time = new Date().getTime();

    return $.ajax({
        url: 'UploadFile?time=' + time,
        type: 'POST',
        cache: false,
        contentType: false,
        processData: false,
        data: formData,
        success: function(uploadResult) {
            $.ajax({
                url: 'UploadFile?time=' + time,
                type: 'GET',
                dataType: 'json',
                success: function(res){
                    var fileName = res.progress.filename;
                    var tpl = "<li><a href='Provider?type=getattach&key=" + fileName + "'>" + fileName + "</a></li>";
                    $('.js-uploaded-files').append(tpl);
                },
                error: function(err){
                    console.log(err);
                }
            });

            fileInput.form.reset();
            return uploadResult;
        },
        error: function(err) {
            fileInput.form.reset();
            console.log(err);
        }
    });
}
