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
        }

    }

    $scope.fillTw = function () {

        $scope.text = "Max is at the park Max's dad is at the park Kitty is at the park too I can race you to the swings says Max Max and Kitty run to the swings Dad run too Max gets to the swings Look I am at the swrings says Max Kitty and Max get onto the swings Dad can not get onto the swings He is too big Can you push me syas Max Can you push me says Kitty Dad pushes Kitty and Max They are going up Max sees a red slide I can race you to the red slide says Max Max and Kitty run to the red slide Dad runs too Kitty gets to the red slide Look I am at the red slide says Ktty Kitty and Max get onto the slide Dad cannot get onto the slide He is too big Kitty is going down Max is going down too Look out Kitty says Max Here I am I can rece you to the see-saw says Kitty Kitty and Max run to the see-saw Dan runs too Ktty jumps onto the see-saw Max jumps onto the see-saw too Dan cannot get onto the see-saw He is too big Kitty is going up on the see-saw Max is going down on the see-saw They are going up and down on the see-saw Max sees a little horse He jumps down and runs to the little horse Look out kitty says dad Kitty and Dan run to Max Max is on the little horse Kitty gets onto the little horse too Dad cannot get onto the littel horse He is too big Dad pushes the little horse This is fun says Kitty This is fun says Max Kitty sees a big horse Look says Kitty I can see a big horse Kitty runs to the big horse Max sees the big horse and run to it Dad runs to the big horse too can I pet the big horse say Kitty Can I pet the big horse says Max Yes can pet the big horse says Dad Max and Kitty cannot pet the big horse The horse is too big The big horse looks down at kitty and Max They look up at the big horse They cannot pet the big horse Dan can pet the big horse The horse is big and Dad is big Up up says Dad The horse is big and Dad is big says Max I the he she me we be was to do are all you your come some said here there they go no so my one by like have live give";
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
// Can you push me syas Max.\
// Can you push me says Kitty.\
// Dad pushes Kitty and Max.\
// They are going up.\
// Max sees a red slide.\
// I can race you to the red slide says Max.\
// Max and Kitty run to the red slide.\
// Dad runs too.\
// Kitty gets to the red slide.\
// Look I am at the red slide says Ktty.\
// Kitty and Max get onto the slide.\
// Dad cannot get onto the slide.\
// He is too big.\
// Kitty is going down.\
// Max is going down too.\
// Look out Kitty says Max.\
// Here I am.\
// I can rece you to the see-saw says Kitty.\
// Kitty and Max run to the see-saw.\
// Dan runs too.\
// Ktty jumps onto the see-saw.\
// Max jumps onto the see-saw too.\
// Dan cannot get onto the see-saw.\
// He is too big.\
// Kitty is going up on the see-saw.\
// Max is going down on the see-saw.\
// They are going up and down on the see-saw.\
// Max sees a little horse.\
// He jumps down and runs to the little horse.\
// Look out kitty says dad.\
// Kitty and Dan run to Max.\
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
// Dan can pet the big horse.\
// The horse is big and Dad is big.\
// Up up says Dad.\
// The horse is big and Dad is big says Max.\
// I the he she me we be was to do are all you your come some said here there they go no so my one by like have live give

          $scope.sections.tw.items = $scope.text.split(" ");

//        $scope.sections.tw.items = ["Sethuki",
//                                       "Ashvin",
//                                       "Dad",
//                                       "Max",
//                                       "Kitty",
//                                       "swing",
//                                       "too",
//                                       "the",
//                                       "I",
//                                       "run",
//                                       "they",
//                                       "she",
//                                       "he",
//                                       "me",
//                                       "we",
//                                       "be",
//                                       "happy",
//                                       "gets",
//                                       "get",
//                                       "be",
//                                       "be",
//                                      ""];
    }

    $scope.fillNum = function () {
        for (var i=20; i <= 100; i++) {
            $scope.sections.num.items.push(i);
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
        $scope.words = $scope.selectedSection.items;
        $scope.word = $scope.words[Math.floor(Math.random()* $scope.words.length)];
    }

    $scope.next = function() {
        $scope.temp = "";
        do {
            $scope.temp = $scope.words[Math.floor(Math.random()* $scope.words.length)];
            console.log($scope.temp );
            console.log($scope.word );
            console.log($scope.word == $scope.temp);
        } while ($scope.temp==="" || $scope.word === $scope.temp);
        $scope.word=$scope.temp;
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

