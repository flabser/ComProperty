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