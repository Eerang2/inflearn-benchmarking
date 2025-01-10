const chaptersContainer = document.getElementById('chaptersContainer');

document.getElementById('addChapter').addEventListener('click', () => {
    const chapterName = document.getElementById('chapterName').value.trim();
    if (!chapterName) {
        alert('단원명을 입력하세요.');
        return;
    }
    const chapterDiv = document.createElement('div');
    chapterDiv.classList.add('chapter');

    const chapterTitle = document.createElement('h3');
    chapterTitle.textContent = chapterName;
    chapterDiv.appendChild(chapterTitle);

    const videoInput = document.createElement('input');
    videoInput.type = 'file';
    videoInput.accept = 'video/*';
    videoInput.placeholder = '영상 업로드';
    chapterDiv.appendChild(videoInput);

    const videoTitle = document.createElement('input');
    videoTitle.type = 'text';
    videoTitle.placeholder = '영상 제목 입력';
    chapterDiv.appendChild(videoTitle);

    const addButton = document.createElement('button');
    addButton.textContent = '추가';
    addButton.addEventListener('click', () => {
        const uploadedVideo = document.createElement('p');
        uploadedVideo.textContent = `${videoTitle.value} - ${videoInput.files[0]?.name || '파일 없음'}`;
        chapterDiv.appendChild(uploadedVideo);
        videoInput.value = '';
        videoTitle.value = '';
    });
    chapterDiv.appendChild(addButton);

    chaptersContainer.appendChild(chapterDiv);
    document.getElementById('chapterName').value = '';
});

document.getElementById('finish').addEventListener('click', () => {
    alert('강의 등록이 완료되었습니다!');
    // 등록 완료 로직 추가
});
