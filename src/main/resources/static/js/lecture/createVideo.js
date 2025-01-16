const chaptersContainer = document.getElementById('chaptersContainer');

// 단원 추가 버튼 클릭
document.getElementById('addChapter').addEventListener('click', () => {
    const chapterName = document.getElementById('chapterName').value.trim();
    if (!chapterName) {
        alert('단원명을 입력하세요.');
        return;
    }

    // 단원 div 생성
    const chapterDiv = document.createElement('div');
    chapterDiv.classList.add('chapter');

    // 단원 제목 (단원명)
    const chapterTitle = document.createElement('h3');
    chapterTitle.textContent = chapterName;
    chapterDiv.appendChild(chapterTitle);

    // 영상 추가 버튼 생성
    const addVideoButton = document.createElement('button');
    addVideoButton.textContent = '영상 추가';
    chapterDiv.appendChild(addVideoButton);

    // 영상 제목과 파일 input을 감싸는 div 생성 (초기 상태는 비어있음)
    const videoContainer = document.createElement('div');
    chapterDiv.appendChild(videoContainer);

    // 영상 추가 버튼 클릭 시
    addVideoButton.addEventListener('click', () => {
        // 영상 제목과 파일 input 생성
        const videoItem = document.createElement('div');

        // 영상 파일 input 생성
        const videoInput = document.createElement('input');
        videoInput.type = 'file';
        videoInput.accept = 'video/*';
        videoInput.placeholder = '영상 업로드';
        videoItem.appendChild(videoInput);

        // 영상 제목 input 생성
        const videoTitle = document.createElement('input');
        videoTitle.type = 'text';
        videoTitle.placeholder = '영상 제목 입력';
        videoItem.appendChild(videoTitle);

        // 추가된 영상 항목을 videoContainer에 추가
        videoContainer.appendChild(videoItem);
    });

    // 단원 추가 후 단원 리스트에 추가
    chaptersContainer.appendChild(chapterDiv);
    document.getElementById('chapterName').value = ''; // 단원명 초기화
});
document.getElementById('finish').addEventListener('click', () => {
    const chapters = [];

    // 각 단원과 그 안의 영상 데이터 수집
    document.querySelectorAll('#chaptersContainer .chapter').forEach(chapterDiv => {
        const chapterName = chapterDiv.querySelector('h3').textContent;
        const videos = [];

        chapterDiv.querySelectorAll('div input[type="file"]').forEach((fileInput, index) => {
            const videoTitleInput = chapterDiv.querySelectorAll('div input[type="text"]')[index];
            if (fileInput.files.length > 0 && videoTitleInput.value.trim()) {
                videos.push({
                    videoTitle: videoTitleInput.value.trim(),
                    file: fileInput.files[0]
                });
            }
        });

        chapters.push({ chapterName, videos });
    });

    // FormData에 데이터 추가
    const formData = new FormData();
    const metadata = [];

    chapters.forEach((chapter, chapterIndex) => {
        const chapterData = { chapterName: chapter.chapterName, videos: [] };

        chapter.videos.forEach((video, videoIndex) => {
            chapterData.videos.push({ videoTitle: video.videoTitle });
            formData.append(`videoFiles`, video.file);
        });

        metadata.push(chapterData);
    });

    // 메타데이터 추가
    formData.append('metadata', JSON.stringify(metadata));

    // AJAX 요청
    $.ajax({
        url: '/api/save/lecture-video',
        method: 'POST',
        data: formData,
        processData: false,
        contentType: false,
        success: (response) => {
            alert('강의 등록이 완료되었습니다!');
        },
        error: (xhr) => {
            console.error(xhr.responseText);
            alert('강의 등록에 실패하였습니다.');
        }
    });
});
