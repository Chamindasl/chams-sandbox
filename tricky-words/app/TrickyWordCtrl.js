angular.module('myApp').controller('TrickyWordCtrl',
function TrickyWordCtrl($scope, $timeout) {

    $scope.sections = {
        tw: {
            name: 'Tricky Words',
            items : []
        },
        num: {
            name: 'Numbers',
            items : []
        },
        ABC: {
            name: 'ABC',
            items : []
        },
        abc: {
            name: 'abc',
            items : []
        },
        AaBbCc :{
            name: 'AaBbCc',
            items : []
        },
        readingBigHorse :{
            name: 'Reading Big Horse',
            items : "Max is at the park. Max's dad is at the park. Kitty is at the park too. I can race you to the swings says Max. Max and Kitty run to the swings. Dad run too. Max gets to the swings. Look I am at the swrings says Max. Kitty and Max get onto the swings. Dad can not get onto the swings. He is too big. Can you push me syas Max. Can you push me says Kitty. Dad pushes Kitty and Max. They are going up. Max sees a red slide. I can race you to the red slide says Max. Max and Kitty run to the red slide. Dad runs too. Kitty gets to the red slide. Look I am at the red slide says Ktty. Kitty and Max get onto the slide. Dad cannot get onto the slide. He is too big. Kitty is going down. Max is going down too. Look out Kitty says Max. Here I am. I can rece you to the see-saw says Kitty. Kitty and Max run to the see-saw. Dan runs too. Ktty jumps onto the see-saw. Max jumps onto the see-saw too. Dan cannot get onto the see-saw. He is too big. Kitty is going up on the see-saw. Max is going down on the see-saw. They are going up and down on the see-saw. Max sees a little horse. He jumps down and runs to the little horse. Look out kitty says dad. Kitty and Dan run to Max. Max is on the little horse. Kitty gets onto the little horse too. Dad cannot get onto the littel horse. He is too big. Dad pushes the little horse. This is fun says Kitty. This is fun says Max. Kitty sees a big horse. Look says Kitty. I can see a big horse. Kitty runs to the big horse. Max sees the big horse and run to it. Dad runs to the big horse too. can I pet the big horse say Kitty. Can I pet the big horse says Max. Yes can pet the big horse says Dad. Max and Kitty cannot pet the big horse. The horse is too big. The big horse looks down at kitty and Max. They look up at the big horse. They cannot pet the big horse. Dan can pet the big horse. The horse is big and Dad is big. Up up says Dad. The horse is big and Dad is big says Max.".split("."),
            isReading: true
        }
    }

    $scope.readingWordIndex = 0;
    $scope.readingLineIndex = 0;
    $scope.readingLine = [];

    $scope.fillReadingBigHorse = function () {
        $scope.text = "Max is at the park. Max's dad is at the park. Kitty is at the park too. I can race you to the swings says Max. Max and Kitty run to the swings. Dad run too. Max gets to the swings. Look I am at the swrings says Max. Kitty and Max get onto the swings. Dad can not get onto the swings. He is too big. Can you push me syas Max. Can you push me says Kitty. Dad pushes Kitty and Max. They are going up. Max sees a red slide. I can race you to the red slide says Max. Max and Kitty run to the red slide. Dad runs too. Kitty gets to the red slide. Look I am at the red slide says Ktty. Kitty and Max get onto the slide. Dad cannot get onto the slide. He is too big. Kitty is going down. Max is going down too. Look out Kitty says Max. Here I am. I can rece you to the see-saw says Kitty. Kitty and Max run to the see-saw. Dan runs too. Ktty jumps onto the see-saw. Max jumps onto the see-saw too. Dan cannot get onto the see-saw. He is too big. Kitty is going up on the see-saw. Max is going down on the see-saw. They are going up and down on the see-saw. Max sees a little horse. He jumps down and runs to the little horse. Look out kitty says dad. Kitty and Dan run to Max. Max is on the little horse. Kitty gets onto the little horse too. Dad cannot get onto the littel horse. He is too big. Dad pushes the little horse. This is fun says Kitty. This is fun says Max. Kitty sees a big horse. Look says Kitty. I can see a big horse. Kitty runs to the big horse. Max sees the big horse and run to it. Dad runs to the big horse too. can I pet the big horse say Kitty. Can I pet the big horse says Max. Yes can pet the big horse says Dad. Max and Kitty cannot pet the big horse. The horse is too big. The big horse looks down at kitty and Max. They look up at the big horse. They cannot pet the big horse. Dan can pet the big horse. The horse is big and Dad is big. Up up says Dad. The horse is big and Dad is big says Max.";
    }

    $scope.fillTw = function () {

        $scope.text = "Max is at the park Max's dad is at the park Kitty is at the park too I can race you to the swings says Max Max and Kitty run to the swings Dad run too Max gets to the swings Look I am at the swrings says Max Kitty and Max get onto the swings Dad can not get onto the swings He is too big Can you push me says Max Can you push me says Kitty Dad pushes Kitty and Max They are going up Max sees a red slide I can race you to the red slide says Max Max and Kitty run to the red slide Dad runs too Kitty gets to the red slide Look I am at the red slide says Kitty Kitty and Max get onto the slide Dad cannot get onto the slide He is too big Kitty is going down Max is going down too Look out Kitty says Max Here I am I can race you to the see-saw says Kitty Kitty and Max run to the see-saw Dad runs too Kitty jumps onto the see-saw Max jumps onto the see-saw too Dad cannot get onto the see-saw He is too big Kitty is going up on the see-saw Max is going down on the see-saw They are going up and down on the see-saw Max sees a little horse He jumps down and runs to the little horse Look out kitty says dad Kitty and Dad run to Max Max is on the little horse Kitty gets onto the little horse too Dad cannot get onto the little horse He is too big Dad pushes the little horse This is fun says Kitty This is fun says Max Kitty sees a big horse Look says Kitty I can see a big horse Kitty runs to the big horse Max sees the big horse and run to it Dad runs to the big horse too can I pet the big horse say Kitty Can I pet the big horse says Max Yes can pet the big horse says Dad Max and Kitty cannot pet the big horse The horse is too big The big horse looks down at kitty and Max They look up at the big horse They cannot pet the big horse Dad can pet the big horse The horse is big and Dad is big Up up says Dad The horse is big and Dad is big says Max";
//Max is at the park.\
//Max's dad is at the park.\
// Kitty is at the park too.\
// I can race you to the swings says Max.\
// Max and Kitty run to the swings.\
// Dad run too.\
// Max gets to the swings.\
// Look I am at the swrings says Max.\
// Kitty and Max get onto the swings.\
// Dad can not get onto the swings.\
// He is too big.\
// Can you push me says Max.\
// Can you push me says Kitty.\
// Dad pushes Kitty and Max.\
// They are going up.\
// Max sees a red slide.\
// I can race you to the red slide says Max.\
// Max and Kitty run to the red slide.\
// Dad runs too.\
// Kitty gets to the red slide.\
// Look I am at the red slide says Kitty.\
// Kitty and Max get onto the slide.\
// Dad cannot get onto the slide.\
// He is too big.\
// Kitty is going down.\
// Max is going down too.\
// Look out Kitty says Max.\
// Here I am.\
// I can race you to the see-saw says Kitty.\
// Kitty and Max run to the see-saw.\
// Dad runs too.\
// Kitty jumps onto the see-saw.\
// Max jumps onto the see-saw too.\
// Dad cannot get onto the see-saw.\
// He is too big.\
// Kitty is going up on the see-saw.\
// Max is going down on the see-saw.\
// They are going up and down on the see-saw.\
// Max sees a little horse.\
// He jumps down and runs to the little horse.\
// Look out kitty says dad.\
// Kitty and Dad run to Max.\
// Max is on the little horse.\
// Kitty gets onto the little horse too.\
// Dad cannot get onto the littel horse.\
// He is too big.\
// Dad pushes the little horse.\
// This is fun says Kitty.\
// This is fun says Max.\
// Kitty sees a big horse.\
// Look says Kitty.\
// I can see a big horse.\
// Kitty runs to the big horse.\
// Max sees the big horse and run to it.\
// Dad runs to the big horse too.\
// can I pet the big horse say Kitty.\
// Can I pet the big horse says Max.\
// Yes can pet the big horse says Dad.\
// Max and Kitty cannot pet the big horse.\
// The horse is too big.\
// The big horse looks down at kitty and Max.\
// They look up at the big horse.\
// They cannot pet the big horse.\
// Dad can pet the big horse.\
// The horse is big and Dad is big.\
// Up up says Dad.\
// The horse is big and Dad is big says Max.\
// I the he she me we be was to do are all you your come some said here there they go no so my one by like have live give

          var splited = $scope.text.split(" ");
          for (var i = 0; i < splited.length; i++) {
            if ($scope.sections.tw.items.indexOf(splited[i]) === -1) {
                $scope.sections.tw.items.push(splited[i]);
            }
          }

    }

    $scope.fillNum = function () {
        for (var i=20; i <= 100; i++) {
            $scope.sections.num.items.push(i + "");
        }
    }

    $scope.abcs = function () {
        for (var i='A'.charCodeAt(0); i <= 'Z'.charCodeAt(0); i++) {
            $scope.sections.ABC.items.push(String.fromCharCode(i));
            $scope.sections.abc.items.push(String.fromCharCode(i + 32));
            $scope.sections.AaBbCc.items.push("" + String.fromCharCode(i + 32) + "   " + String.fromCharCode(i));
        }
    }

    $scope.updateSection = function() {
        $scope.words = angular.copy($scope.selectedSection.items);
        $scope.next();
    }

    $scope.nextReading = function() {

        var line = $scope.words[$scope.readingLineIndex].split(" ");

        $scope.wordMid = line[$scope.readingWordIndex].trim()

        for (var i = 0; i < $scope.readingWordIndex; i++) {
            $scope.lineLeft = $scope.lineLeft + " " + line [i];
        }

        for (var i = $scope.readingWordIndex + 1 ; i < line.length; i++) {
            $scope.lineRight = $scope.lineRight + " " + line[i];
        }

        $scope.readingWordIndex++;

        if ($scope.readingWordIndex >= line.length) {
            $scope.readingLineIndex++;
            $scope.readingWordIndex = 0;
        }

        if ($scope.readingLineIndex >= $scope.words.length) {
            $scope.readingLineIndex == 0;
        }

        if ($scope.wordMid.length<=0) {
            $scope.next();
        }

    }

    $scope.next = function() {

        $scope.lineLeft = '';
        $scope.wordMid = '';
        $scope.lineRight = '';
        $scope.word = '';

        if ($scope.selectedSection.isReading) {
            $scope.nextReading();
        } else {
            $scope.nextOther();
        }

    }

    $scope.nextOther = function() {
        if ($scope.words.length<=0) {
            $scope.words = angular.copy($scope.selectedSection.items);
        }
        $scope.word = $scope.words[Math.floor(Math.random() * $scope.words.length)];
        $scope.words.splice($scope.words.indexOf($scope.word), 1);

    }

    $scope.auto = function() {
        if ($scope.autoPlay) {
            $scope.next();
            $timeout( function(){ $scope.auto(); }, 10000);
        }
    }


    $scope.fillTw();
    $scope.fillNum();
    $scope.abcs();

    $scope.selectedSection = $scope.sections.num

    $scope.autoPlay=true;

    $scope.updateSection();
    $scope.auto();

}
);

