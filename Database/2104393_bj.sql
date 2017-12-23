-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: fdb14.biz.nf
-- Generation Time: Jun 26, 2016 at 07:36 PM
-- Server version: 5.5.38-log
-- PHP Version: 5.5.36

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `2104393_bj`
--

-- --------------------------------------------------------

--
-- Table structure for table `bdblog`
--

CREATE TABLE `bdblog` (
  `ID` int(11) NOT NULL,
  `title` text NOT NULL,
  `source` text NOT NULL,
  `time` text NOT NULL,
  `content` longtext NOT NULL,
  `pic` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bdblog`
--

INSERT INTO `bdblog` (`ID`, `title`, `source`, `time`, `content`, `pic`) VALUES
(1, 'Blood Donation Tips -Before, During & After', 'American Red Cross', '18 June,2016', 'Before Your Donation-\n1.)Maintain a healthy iron level in your diet by eating iron rich foods, such as red meat, fish, poultry, beans, spinach, iron-fortified cereals and raisins.\n\n2.)Get a good night''s sleep.\n\n3.)Drink an extra 16 oz. of water or nonalcoholic fluids before the donation.\n\n4.)Eat a healthy meal before your donation. Avoid fatty foods, such as hamburgers, fries or ice cream before donating. (Fatty foods can affect the tests we do on your blood. If there is too much fat in your blood, your donation cannot be tested for infectious diseases and the blood will not be used for transfusion.)\n\n5.)If you are a platelet donor, remember that your system must be free of aspirin for two days prior to donation.\n\n6.)Remember to bring your donor card, driver''s license or two other forms of ID.\n\n\n\nDuring Your Donation-\n1.)Wear clothing with sleeves that can be raised above the elbow.\n\n2.)Let the person taking your blood know if you have a preferred arm and show them any good veins that have been used successfully in the past to draw blood.\n\n3.)Relax, listen to music, talk to other donors or read during the donation process.\n\n4.)Take the time to enjoy a snack and a drink in the refreshments area immediately after donating.\n\n\nAfter Your Donation-\n1.)Drink an extra four (8 ounce) glasses of liquids and avoid alcohol over the next 24 hours.\n\n2.)Remove the wrap bandage (if you had one put on your arm) within the next hour.\n\n3.)Keep the strip bandage on for the next several hours.\n\n4.)To avoid a skin rash, clean the area around the strip bandage with soap and water.\n\n5.)Do not do any heavy lifting or vigorous exercise for the rest of the day.\n\n6.)If the needle site starts to bleed, apply pressure to it and raise your arm straight up for about 5-10 minutes or until bleeding stops.\n\n7.)If you experience dizziness or lightheadedness after donation, stop what you are doing and sit down or lie down until you feel better. Avoid performing any activity where fainting may lead to injury for at least 24 hours.\n\nCall us at 1-866-236-3276 to report any additional health information that you forgot to tell us, if you have any problems or if you needed medical care after giving blood.Learn more about your need for iron after blood donation at www.redcrossblood.org/iron\n', 'http://www.redcrossblood.org/sites/arc/files/images/donation_tips_salad-water.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `bddonorregister`
--

CREATE TABLE `bddonorregister` (
  `IDno` int(11) NOT NULL,
  `gender` text NOT NULL,
  `age` text NOT NULL,
  `weight` text NOT NULL,
  `donortype` text NOT NULL,
  `lastdonation` text NOT NULL,
  `smsalert` text NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `id` text NOT NULL,
  `type` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bddonorregister`
--

INSERT INTO `bddonorregister` (`IDno`, `gender`, `age`, `weight`, `donortype`, `lastdonation`, `smsalert`, `created_at`, `id`, `type`) VALUES
(2, 'Male', '21', '78', 'On Need Basis', '1/5/2016', 'Yes', '2016-06-23 20:13:46', 'bobby', 'registered');

-- --------------------------------------------------------

--
-- Table structure for table `bdevent`
--

CREATE TABLE `bdevent` (
  `ID` int(11) NOT NULL,
  `venue` text NOT NULL,
  `time` text NOT NULL,
  `contactperson` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `bdevents`
--

CREATE TABLE `bdevents` (
  `ID` int(11) NOT NULL,
  `venue` text NOT NULL,
  `time` text NOT NULL,
  `contactperson` text NOT NULL,
  `event` text NOT NULL,
  `date` text NOT NULL,
  `description` longtext NOT NULL,
  `mobile` text NOT NULL,
  `latitude` text NOT NULL,
  `longitude` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bdevents`
--

INSERT INTO `bdevents` (`ID`, `venue`, `time`, `contactperson`, `event`, `date`, `description`, `mobile`, `latitude`, `longitude`) VALUES
(1, 'M.O.G. line,Indore 452001', '10:00 a.m. to 5:00 p.m.', 'P Narhari', 'Indian RedCross Society Blood Donation Camp', '22/06/2016', 'Indian red cross society district branch Indore organizes various programs for people awareness about health. Society endeavor through various dialogues, such as street theater, railey , camps training, etc. so that a healthy society can be build. On these footprints we move to organize various blood donation camps in the guidance of district collector. ', '0731-2361056', '22.7079948', '75.8395961'),
(2, 'Collectorate Campus,Moti tabela ,Indore 452001', '9:30 a.m. to 1:00 p.m.', 'Sanjay Maheshwari', 'Lions Club Indore Training Camp', '27/06/2016', 'Every 2 seconds someone needs blood. Your blood helps more than one life at a time.\r\nWe are Lions Club Indore Mahanagar. We are NGOs which always care for people around us.And as a part of our mission to help others we have created a blood donation website.Whenever a Lions club gets together, problems get smaller. And communities get better.Thats because we help where help is needed in our own communities and around the world with unmatched integrity and energy.Our 45000 clubs and 1.35 million members make us the worlds largest service club organization', '+91-9826057110', '22.7088', '75.8537');

-- --------------------------------------------------------

--
-- Table structure for table `bdfbsignup`
--

CREATE TABLE `bdfbsignup` (
  `id` int(11) NOT NULL,
  `fbname` text NOT NULL,
  `fbid` varchar(20) NOT NULL,
  `fbemail` text NOT NULL,
  `fbgender` text NOT NULL,
  `fbpic` text NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `fbstate` text NOT NULL,
  `fbdistrict` text NOT NULL,
  `fbmobile` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bdfbsignup`
--

INSERT INTO `bdfbsignup` (`id`, `fbname`, `fbid`, `fbemail`, `fbgender`, `fbpic`, `created_at`, `fbstate`, `fbdistrict`, `fbmobile`) VALUES
(1, 'Bobby', '103343390263', '', '', 'https://graph.facebook.com/103342390263/picture?type=normal', '2016-06-22 07:39:38', '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `bdforgetpassword`
--

CREATE TABLE `bdforgetpassword` (
  `email` varchar(50) NOT NULL,
  `OTP` varchar(10) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `bdglsignup`
--

CREATE TABLE `bdglsignup` (
  `id` int(11) NOT NULL,
  `glname` text NOT NULL,
  `glid` varchar(50) NOT NULL,
  `glpic` text NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `glemail` text NOT NULL,
  `glstate` text NOT NULL,
  `gldistrict` text NOT NULL,
  `glmobile` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bdglsignup`
--

INSERT INTO `bdglsignup` (`id`, `glname`, `glid`, `glpic`, `created_at`, `glemail`, `glstate`, `gldistrict`, `glmobile`) VALUES
(7, 'Bobby', '109653458013318', '', '2016-06-11 05:42:36', 'bobby.sixthsense@gmail.com', '', '', ''),
(6, 'bobby', '10724959225145', '', '2016-06-10 17:10:39', 'bobby@gmail.com', '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `bdsignup`
--

CREATE TABLE `bdsignup` (
  `username` varchar(30) NOT NULL,
  `mobile` text NOT NULL,
  `password` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `id` int(11) NOT NULL,
  `state` text NOT NULL,
  `district` text NOT NULL,
  `bloodgroup` text NOT NULL,
  `pic` longtext NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bdsignup`
--

INSERT INTO `bdsignup` (`username`, `mobile`, `password`, `email`, `id`, `state`, `district`, `bloodgroup`, `pic`, `created_at`) VALUES
('bj', '982724', 'Bobby', 'bobby@gmail.com', 6, 'Madhya Pradesh', 'Indore district', 'B+', '/9j/4AAQSBAQAAAQABAAD/2wBDAAEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEB\nAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQH/2wBDAQEBAQEBAQEBAQEBAQEBAQEBAQEB\nAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQH/wAARCAHsApADASIA\nAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQA\nAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3\nODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWm\np6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEA\nAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSEx\nBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElK\nU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3\nuLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD+Ua2g\nUZDNuydx3fKFxI4U7drDdjcFXZnbkBA2+r6J0UozERqoZdx+UfdLFoyPvMcYXJCk4OGFTQxgttO3\neGZggCKu0O20DhlRQcEMfm5UKCMZ0II/k4RA20bseWnLMu5UKthsADzNu4MxOCUU49FWV7q+n66P\nf718m7mEW5q6nbXZaq6lazTvp5rXvd6kSRKx3KMlCqoxVyxYtOdu5YiyHgsN2ECkckopOlFESCqh\nZGLE7XU4U7lVhkwknKlGIZtxVhyyqxWzFEi5ARWDbdpYRMrNuckIo44AVuHLMWI2khwbEKhBkoih\nncbN4KruIZcAu+Adq84ZsYjAMgyNqEU22+lra9byavaWy0a03vdtxaT5m7q99Etu3Muq9beq8m2x\n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\nTdtLDJ4EtfD914Ot7n4c6VcaVqem6boIvdQvm0i18UXV94hvdVi1SbyrKP7Zf36eSP20eEbdnQKF\nkBViWw7SEhgMHbu5LEk7di7jgkLHCq7zHndgsrSH5HbaMZIBIVWK4wMZB5wprOeJpzoqi8LRSi6j\ni056SqQqU3LlcrOXLO93rzKnJvmjcKdH2VaVaM5Xl7JSWnvRo1HUjDmaclFyvePNblnUh8M6if5a\nfEDwr+2RqvxC8caj4V1/xbYeF28XfFkeGLOy/wCEHWzj8N6P8GLa6+GvkreaBd37DW/iVJNY3Jub\ng3FxEj2sptoiLivFrTwl/wAFD7Ox+Ll9Hb3MNzf30134Q8KwaP8ADxfDWpy+IvFWpxeIbi/s4NK8\n/UHt9AMU1vPf6jLdTXH2Z9Slv/MuI3/aFwFmcIQ/AyXK5DsxBGzDcP8ANsQlirNjJDFhERlAsmQS\nnzFmyW+abbhuMsVHzKgyQ6gNjJq3jISvzYPDNupCbag1J8lepW5G+Z+61P2ataapKMeZtcxksIk9\nK9ayo+xUXKLj/DpwdS3Jd1JOmpycm1zuTio3nf8AFLULf9tz4WfB7RPFes+KNT8PzaVrNz4f1zTZ\ndK8Dx6HpnhW58BeELbQ59IsNI0FFtdXk8Z6hren6Z5a3NlFdpbq+nSWFpJBL98/s6eJ77xT4Aspr\n688Y+Iru3lvvtvjLxTo40iDXbw6vexSJoMZ03SVudM042yWdrcrp0Xn2scF2bid5kuW+ojp8NxFc\nQ3sMd3as65guIY2jPlOMo6sShDP+8DMS6h5HbIjiJpLFbQrHawp5UFvHHHHCkYhVFKkDyYwEVUQ+\nYFCllAUqgVlElclWrTqqHJTVOpS9pdwsoVFOrUlHmi1dygpKnF81uSMLpyXM+2jek6kZSda8KfK5\nO7jyKmpa3fxSg5y096pOUublXK8S4TyrdcqcDIDqg3jiUB96Mfl3AkBc/MU27gM1SEbQDKRBydoJ\nDDAZlkcszAKgLsrc8qNwIIG811F0FkiIwgVEVVLYZkADBc7ixz8/7sBvlYK2WKE1UiAWA7s5VmVh\n8pA+YCNsth13KDtVT5Z8xhv8pQ1YNrlb0+KNtba8zt6tq+mvZ6rRwvKNVpe87LVdXOKtvZcytZ62\n1V7xkzj5FaOXDKQj4kJw5Ef3vLcjaoJlBRTxtYKAM4Zar3aLubO0p/FhsgfM6MPu4EhQb2U4ZC4J\nAADnonjRpSfnXI2qwjySd5AHzKuf+Whk3KQhfBXAVXpajawqSmMiMIp8vf8A6xmIBLA5BcBm4bCn\nHBbEdZ8/vW68iaaUtnZu76apNXb1tdX5XKoxd4Lm0bTsmk1709Vfq+VbPRt6NnMT2qeVkgqBiPpk\nruACtlTligMbqvzYwy7sA5yWtV8hmXBwjeYojGGJZgx58xwQMPlcBlLFwy8HtZbOMW7EOz7S7ANw\ny8MilwM4VsrhAAQcAjBLHBWzcxToXy0cWGOxlcpvdSUViVXJTfncx2kFtz8VxOpde89E1fR2s3PZ\na7bdd1rdXekYtqp1b5eVu+6u1d3fRbNW953d7M5BrSNJWVVQqNh+bG44JjHzEgHdgYzkpuztIy68\n9cwTRSkrhvLTaWRAGILPs2guGGADmMNwcqVbHmHsmhZJnVAS4cAgKPnDLkMSpIMmAGMcZwzjYRkq\n5xbmFkdgI2LsUw23CfK2HYKSSoCneRyrGQKB8ozzNq8npZpK+vRu3Xu39+zZ0U48tnLl1grWd93r\nf5WvvZtXb1OJvIiJHZos/MSfKySCGLFTvwQiDhcEv84PmF8VhX6mRSyr02JvVTg8PhgNw+YZJAYk\noGKsrBY3rtLhB5pyN6oHVj5Z+cgMc5ZgWOfvFhhhtLAsqMMC6hhcSKpJVW3knC7y2QEIVSSu3cuA\n5ZhglcMFOfPG3X0+/wDyvv1Wt0xpQSVop2cdGtHZ6X8/dTWr63V078HewxyIzeXhWxuJPyrgSKd3\nLjnJiyCuSzKSwU7+ZvrRFgEUTZb51DHLRlWDqpIL9UBJyik4dwzliNveXungxgljkMrKEjfIZSAS\noO3+IFSCCwwzbgMSNyt5aTLEfmXcFYqWUE4ZmYlSqBW7hE2ucgJvdtzORaa0a0S07azXe/8A+0tX\nbU5OaCu07Si1dPRqUlo07rrfya30vws9rOLZ1iGCQw3hgzkAs2RGX2hSoUDJyod3XL5auZuIplgd\nTAXU5y5xhNoYgM2WG0EBRNuVACJFJOXPfXEcsVo0b5wGk+cF8ovkuVHlKrBsYYOmGBAQbmypPOSY\na3mIEce2RVEj4BkH707nVVZC8ZZGBQHBwpj2ocaU2uaOq+KPo7Sm7vV66aK2qctbpmMmv37a0VlF\npta3SSik9FvzPzim2nFHzx4Ulkj0/WYdpEq+NPG/yBQV81vGWtFiWLMAgDGIrvRgh8wFldpGuTBT\n5rJ5bvuBJIA+bcWYKfMaItyPMB4PybiZAyGx4WihfT9f4RE/4TLxj8pUoskX/CUawyiAKrlwqllB\nUKHZW2lgH2zS2kX2efykZVDwoAkkgKqFhJIG3hnbPLlQpwAoRcj30ledlr7Wqndu7/ez3Vla91p0\n1V21d+WoyUG1orUrRad0lFt33u1dtXurW0V5HLTQoyMGKPkKuxggQBHkG4HzRvWNcsuVYKd6swVl\nzzxgEau+ELjG1VZFGxgzlyEY7lzhyUdEJVlAYCSQdVJaTsJ4keUcn5WMjoypMwaQrwACDkBWZtxD\nmRWZwefmS8AxHzllY/61dqkEZx5mMkL8q8DlVcgIYyL79V89ZL8bfg922x+9acOZLSHvWV95J397\nsvi6a2bcbnKhpGM2SGKs2d7HJIL4YCMspA2qNpxHkQblRcNWTLJMUlLocMSw2xuCxCnGzywX3MDy\nGJIBMgLEHPR3LEMSyOXwPNcScAq7qCf9IKfMNhy20EM6bgytI2MBCyPuKhupZ2BUYiSNMBLgnDAK\nVZyQxcEFgMCuVWb1WqVrba+uv8y/W9zJSldpO6XKns+rT6O26bu21dWu7MwHmQRyFmXcrOnSaSVC\nkzKVJEZVGGAxAK7SCmxpNi1k4/0V1ERMZBdo4y6iRWMjBtrDa7uBllZxtgU7ixJauiMUTea5iRgu\nQxBUhh5xAYhQ4ZVQqFBOORuIdSWxpLeNY5SGUuFK4DR7eYyGIWOPfyz8l2ZmY+YQcI1RZWtZb/Ld\n9O/X1b1era9/943G9uRaXs488rO2vNZ3cktbNXb3OfeKN7ZnaMKpPy7Gk2kPNLGMB1lCqMbnZW8x\n2cBlLncM2eLbYoUfGHGEwRggSyK0axujKzL5rFtu3c4LoqkoN2W1Btm2E7gELFwhJ3zngOYpZQu0\nYx8xCsRt8tZDWTMsy2ab1B3ENldokJYNsZhswCu5y0e8LtWPDIS+M7Kz5dLp2b31crNNu6fVdVor\nK7bfK0/ilrKLd2n10julFO6vbW290pIy7kzA2uMMAoQ/vMPuDqApXKPvAKyblfzPvow3BHWvdXA/\ntBA8ZCkopfdFyihwSWdyGwAuSwBVVGSSCHu3ksywQkxuM5YtGitkecqsJmSJsCRlUbHbd8zFUlws\nZhuHhe7ty5KBltwysXILOJjlCco2/c2M4Y7/AC2UFWc5W1bvfSOmlt1qvTlem+rvdx1d42SS195p\n6bJtPyt7rs3/ADK6Tcr0Emtv7TuWTy5lfzRMskiqDhDyVJCmQkxqVjUSLIZHiIUvnEWOGWPURG6h\nVSNRskXZudmcuMyFAikqr7iITEwBLQrsHQ+TE2p3Qk8qQLGzMMGLJXOSoWRQxUldwkyEbMrRgB6w\n47Vtt+qyugWQk72ZxktJvYqJE3Nhv3jOFAViScOWN8utul0vyur7ddt9Frdsx52qcVG97LRJdJPu\ntW/d12d9j7QtmhCsFNuEz5mAVyUQTL8jM6b2/dEb1jZmOUQkDDaFnPFvG1oidwGMMfmUlnBZ5jzw\nuDtXG9AWUEmn2tlEwBKzMzRDbGWZ9rDdJt3kjYuwqERnJyGUPzite2S3EciHJLSIuwMzHOCjR56F\nt+07VZiQADmQPXqxtZ8vldfNra/k/uerdr68j0u17sUn1T1ak7dnFSV73100u3DbTLlyIvOITgKs\nY5ywO+X7Vuyz7SHIBCsqIuUO7StpJnYEQ44EhikdWCEAn5P34yQFONjKFIYxqzHcbFqyMnUgoDG0\noNwd5bzdsBxGqdPUsyhgzBlWRjq2sgjkKLDIrYBLNF8hYgKQBLHyu07sF8AFDvSQozawk7Tu72aS\n73d9NX6P0b0dtYcG1GLtf7TTekeZpct/J7W7btXGWy3TGRVRTJhMI6zKfuAnIFyQpzHgrjCkOAAS\nGOzHbu444bcNzjI6lmYPiYAgMRglS53KyyBSAyW7s0u6FdyhW3HyS/mA+aybJBbYGVyQVUozFlaV\nArSDYtfMkldjGeAAV/fBgzFlALG23RHaoG0ErjKMCQ4NRd4p2eqX4uS77aJry5r7anK02lytKMNJ\nN30lK1rLduzsnrezWlya2spHQYkkZlLZjZz8shjlGBIjuQOeYwM7S5O6QlWn+GloW8HWTNLvE19r\n0iBjnDt4k1ZiwldsF3wrKFGD/Dk5Z7cNtKYpHGRKTiMocebjzQ0josGMhim/BV3CFWAJOJvhkky+\nC9NjYZZ7nVzwxwWOu6rvxiNE6g4ynmkgAhMLXLjG/YxaacXO2lu0le99VZJPfVpatnXg4RjOo+Xl\nj7ON3JvrUaVk72td31dm027ancQW4IJEmSCrF/LBJIYgNtkDjdn5h0YL8pcrgDbhtlVnUgBmVCRI\nEweHJKjc+QFCMNrZycEABjUUMDbWZsgHaGx5qFOGA3P8pI3Yyw2jPPJVhWrZ28saHDljgAkvlioD\nKTmVyxkYDaWbGAV+cZDV5flfffzs9Ovf8erZ3wgneUZJv3UpJbWUoyum7a7reztum27VvGqBTuU7\nijBiRuZRkhgGOwuMgrkYxjcSBuOnbGNd74VXzjcjAkgl9xcK24k+mxm3FDICV5ZaWuUlSVJAq7cq\n4TLo27lPLldXU9VGNx2lciRSa2LS2jLIXBzlVXCZ+6ZByS42ghQxJIBYj7xDAn9fn5/1d6vW9fDe\nTu9020r/ABNKzvorq6Xrq2ndtp8rbJnYIzYDLJuVtu4ru6MfvKQAWbO4O5cjO1GzqApRzt2lS20h\ndj/MWcsJMKFHyswyO+DtpIoEVgwb5lKcMF2uRgAgkN0B+U9OCu5gCx3LYIoAcn5QMsqqxU8qW2kA\nHPBMjKSyZwvOaabXV9L7vq+7/q9r2SKSUb27RWt9bcyX/B69Xvd/G/j39rC18E+MPGvhCLw/p92+\nheHJ9Y0fUrzV1Sx13WrDZPqGgbbVJnsWSwuPtsN7LswyXNs1rJKm9srVf2xtX8Iao3h3xN4L0Ndf\n0vWdDbU7XTNfvptLuvBOraTLqx8SaPfXGk2gbUNMhEcd9oU0TzzTSK1nczQujH6z1D4V/DDWkuF1\nj4feFdUW4v59UnN7oOnXO/U7sOlzdmVrVmSW6Qn7QchZCSrqSQ53tD+G/gLRXnbSfBXhuye4szZT\ntDo1ggksC4P2SfFuWa3+VSsJXYAFyuMkdUalCzThK/KkryatJaXur6PWWqvdxTajG75FSxCcL1Iu\n0o30+ypTb36u6V1f3b3vN8x4/wCLP2gofDOsaFcw2UEXgS9MkOpeMNXi1GysdN1cANa6TqoeKO60\nX+14mI0nXruxn8OXN2YLW41K1uXjRti4+OEesW8ln4N0/RdQuZ/DX/CSaRc3XiOzghnt5NKh1FH0\n6WC11GK4ubVZ0iliij2bwcyKsjKNfxp+zf8ADb4la/Za7rVhdwT2WnR6V5dhJDFA8FqJBYNErq32\nCexIAHkKI5odsUsLswesK1/Yu+EIsY9OezvPsqn7Slrss1s49WKPENWtrYW3kWs7wBIblEBhuYQu\n8BixrB1LNpRj7qjvKXvWbTfKouzet9XG9mnZWX02Gyvh6rhYTxXEFbDV6ivOhHJ6tX2Dbs4KtHEq\nM7OLlGSio8rgpNuUreX+C/2uvFl7oOg6Pqfgax1n4g3fh6HXDY6Rf3bWutafPHeyQ3thFY6bqElp\nc3K2jRSWJWS2huXtvtmo21vOHGx4V/bA1h30KHxP4b8NBtX8Y3nhu5h0/WPEdvqOi2dvr50Vr3UL\nSfwrPa2kllczW63b6xe6Os8skaC2triUWp9RT9jL4L3CSR3GlTlFSzktlZbOaO2vLcSq0tvDJBIp\ntJV3Ysj8sPmMsDI2TW3B+xp8EILpdQh8Pj7VFfR38MzxWk08TLIGuo2ke0eSeG4l/eqZt89vuPlz\ntt4pzg1NRhFcyjy80p3i/f5teVXV1FpO8rWTlq2+yGV8IQnzT4px7puUVKMcgqc0qakklrj2oVGk\n4uVmrvmUNk+K+J/7TWpfCHxgbDWP7G1TRdR1jSrTwzBBFJNBqej3rWFlqWpXfirS7+9h8K3vhvUb\nwtd6b4l0O3stU0827aTqstzNcGDxnTP+Cjt3AnifV9b+Fqv4d8NeM5vBl42ieIzfeJPtEqay2l61\nZ6PdaNY2t7pdxJpE/wBtgbV7eexjcPbXN86tG311F+yP8FRd397Z+HJLO41GZnf7NPHGRbXIki1P\nTZna28280zU4ZJEu7K8Zo1WRhE6rkVrWf7IPwOtFK2vht4Io7xblIVm/dm2Cvixn/dq09uhd3tpm\nb7ZakkQXaBuZi4ciU4Jz0vKMppbTu0mtLtxsraJWu7XcwyvhFS14ozeFPla5Fw/TnK7kmveeaKzU\nYuLabTnPmSUafv8Axd4P/bt+Ilt42+IPhO48DWnjSa3vrHxH4as9H163Ft/wh91oekXtxp3hzWDo\nUUmu6/btfrfR6XqcMNodxsv+Ehije1vG7XxJ+37Nofw68OeLL/wv4eB8VaIuoXXh/R/FGuTeMPDV\nlfXN9pFjqE8b+CpNIgZNWgS3b7ff20BDyRxzXGox/wBlyfVNv+x78CvIigfwuJreGadpYXdRHPBP\nvEEVwgjTdLZ4C2t+m27jUBJJJUVWM93+xX+z7qum/wBk6n4Vnu7RrSawmDXslu8waR5La4WW3iV7\na5s8hUltxHDcRFo7yKZJSKuMqKnzOmrc6binOzVktO2tpWT2urv32N5VwfHDzhDivOZVI0oRg58O\n0lzzjz83vLNpaVfdXNO8oLld5SdRr5Lt/wDgondWdr8PdK0nwto/jC88ZWegafZXbeJdRhn07xNd\n3Phq31LR/F93D4H0/SILm0tdcN9JPo0Tz/abZbafRLW3vE1OP9Evgn8RJvit8P7HxfcaTb6LqA1f\nxH4f1nSbe8l1Gzste8La9qOgaomn6jLaadNeWbz2Dz2c1xp1lc+TKqT2yTBhXmln+xn8BLO1iig8\nNPAbfT7WxjnW4KS21zZH/RNWgYplNRjWONGIcW0u1N9uXR69x+Hfwu8M/DQavB4W+329hq9zFfXu\nn3F481i2rkEXurwRyktFf6q6iTUgpMc8w84qJicTKVOy5IKMlJXlzTfur2m0WuW7bi9XolJJtyZx\nY7AcMUcLWqYHP8yxmJtF08NismhhaN3OnzKOIhmGIa9nFVLXo/vLx5uSTR38EeBlFO5iQAqkbv8A\nWNkllYAsRwcqQdox82WvpHKAd6ENgkfNnuQu4KHJBAJb5Sc4ywzuqxDHneBkknGVAJwSSCwzxjbg\nLnOSOuTWgsBJABKnbyx28gHkKO7Z4IyeGIYswOXB3j6afjL/AIHfS2rd7fL+X6b6v59OvRpdGUrV\nmCsgVR8uEJDEFstjIZTtzzkHLDjAZVyFxtlyAAGUFm3EqCGYHb1IOADyMAc5BVg2rawuDMGyyljg\n78gKSduAThVJ3AHadu3BDFSGn+zI5ClQAeSFdTg5BJlKsCcE/Kwyc7uxNUBnIiNnb5bYK7VwpYDc\n4b5gGKbVwxXI/hXaxDE2IgI0YsmY1xwIyedxwQF3ptXnfwoXgtgAsLv2IFWjwoJAGQzLuGWDbtnU\n4PBzuHQMcZMsVpgZOclVVstksu443HcVyWU8lySwTcAQQQCsqNjkA8kbSApChmbdkEZAU4z/ABYX\nJ3gsZYoWAeR+RtAUfdAIL7cqCR0C7cttXCbm3EvUphMZDE7gccsyncrO4J+/n5Tkp0JDhVLhlqwq\nPyCSxHBCjKqMvgkhQVyRweSAB+8cnFC02/qzfn/Wm9gK8caFs5bleoJGWBIX7pbC5UA5LEBSNwzh\nkNsvRiWBOV5Vcqd5+ZSWJGVUByCEwWyABm3C2V3GIDPZ0YMPnx1x1YAZ6AgryELAiRgEBSoCqMjD\n5yCw+V5Nz7cgnGc9VwSgxSk1+CtbVpXt0639dY63T5pUYpadOt3bS6fX71f1bdmqy2kW7KY+RAx6\n7skkOACwwT827IxuPJODTDaDY3A74YrlsZPI2DBBKE44Gd2QM7q0vs6yJtWYoNpBPy7lIMu4glWd\nW+bAGTwT/ES7SxQEKfnWQ4RcngE5xyTuYAkg4JJO4r8zgk6QVk+2nfV2f4ad979nfN+t308km7fN\n7vtotbXOfNmD92TI5VgoGThpFZSGOf4SCGKlGxvYghQ02RVHEbuWYEKPm2/8tI+SFbjAXLAZxkb8\nkmtfyHaYNgLtDhRE6kOHCqAx2Rk4JZlDAgszFgWUGnSQt5YVQcK8e4h0OY9xJ5wAN4QMRuU8k7ty\n7arv5b/fbv8A1+Ilp+GltNHK3Xz/ACV9G3z7W42uWLZRRuO0k/eA4AdsZOSSTkklyWPzGA2UxcKG\nGxQAQIzwB5vJYAoV2tjgq3fcQwUdIIIpFPyMAWUk5w5KOR9/cCApwS3AwQoJCsTWWJI/MVuXOxVj\nz0RWkUfuwXPBAJAJUggq25uWk3td/L1Xfy/LVXu3p72+mivr1krfg/w1urvCa3wm0g7RwOWDyF9y\n7lKgEn58H+LDDaxwTTY7ORY3GW+TaMlWVsE8Y2gggbdpG75fusWZBnbkTyySqPklFkYMDnbK+CEb\nCKwwcgEsVLcHGAiwERrEGJ3O7HcjgMHck4IGFAGXJxxl+QwGS3b5aWvrJX38k7dmtdRJ2vq7O17d\nUnL9LffLVrfmxbOS6vEN+yPcAjhQMOW2fKu1um4PkkMDuAVswvZMDkSA/MNpPmKVXDpztCl1JGAm\n0oCWDYAVq6UQ/MSCybiC0oCA4RwWQbw4/eD5Hwp+UZVhJtYVWiUuSoYEcMfnGQeNoZ2dGPJBUFvm\n4BLlqwe8vXz2vN9fVfjq7Xesdl6L03n+N4r5pbta4Qs3Tf5hJJcKhCPgKWfg5XGMgDkgfMMncoY1\nxYNIwUEMMMEQ+USSC+5vnyrNyQMsGxkSBiMV0oixGScncASGPz7csdxCkZC7j8u0kMy/MCWqrDEW\nUEYCrIyhg6uGAZw+4EKFbdlVQktjcG3ANiSlrtr/AMO1312/Ps28KWxbzm3shDrGiIuPMJEjhyCT\ntIYeXgDcQwIVs4YM+xqsbjJ2s4CllwEjJZWBDHo205b0ZjsYKC26I90hO5XKZCnA+VNoKqFZirF/\n9YpQqG+XBIKsIZYWaIpu8pmYN+5UBiquxYZYEZkWMgnAwhcoysVaj+v6/r5sNv6f6t2OZWyRNxLJ\nvkEax5Z1BBzGVDRhRtc42IMEPvK7lEjGnc2UeRHhid+fnUrgl5SBGrYKktEqruAZflQMQPm6pI90\nYO0ZRGZmJBIPJ4ZWIAVOAFbPzgEYMpOZLCPOy33lzEmOWBchkZWUZAYRLIVUEsAgHzqWaXJXl/dS\n673b28lypvtzW1u2H9fja+/9epiG0RomIc/PyjLvkOxMfM53cFiNrLIzDeZFAYFlrMMW52DoVVJT\nGC2x2ZYd6BthBfYzZ2bmzwdx8xQw6qRYxvYfMflWRlc8lRJjeA4BYqoj+6WwTlh5aqMuFIy8r4DE\nsoAVwqxR7z95iAMFXDBSflBchdrNWOtnd9Yt6L3vefVu+l+Zq7dr6NscGlKV9uXvZJK9+urkl1f2\nk0m0rYOoWiJGcAKWMQyy7T5Tl2ZshcZlSPMaH5cbtw2sTVddOi+zyEM4ICEFhIW2q87M29DtyuwE\n5BYbonBAVa1rmJ8FZEDA/KC0Kv0dlO4qsgzjcCxGG3ICEADlsmY7QpHE53Sb9hkJywZSXdyxf7gV\nS+wx5Z33qXkYzJNuMVdrmj/haTne+m+ui1s277PmqKSoP3ruTjHTV/E7K62fMlu3s9bvmly32BPO\nLeUxO+LdjczBtzZCEDAY4YMWyNw3IxO7dUuoHaR8lvLOxjhgrKf3rkBJOCqvuI5ViQeHJIGzDE8c\n7sigRyM0iqqqrKXkfaPLZZF+UHIJJJdVAVpCjGG7fc7HyjjzmVSiIzAHr90soCSDA5ICnLICRWDs\n3Ozb6Xu9PelbS/bpppe6dtKjzRdOLbTSitdt2t7vRba7N9EkznLi3dLYA/OZGPyqApAIIKlCcdc+\nYTtHmlVQhgAchYp18zAJ2x7CeAG/1nCmRWLByFZ9rZwzNgg7a7G7/eQRjDZCOyli3Qs4O1S20OQ5\n+bG47gvyklWw1aJUuW2qoVMCR8qspZYjmNGA2jGVVW3MxYgABSDxtr2bXRNtvv77Se3lq09Fve6v\n0xVpVZ3VpJJQ1bteOrVt7R0e79Wce/MznayOCFKqjggAESKGPykgrhUVmXCld7BRIMK5SN5TuI2k\nIFViAclpAgwxckkKCq52jJ2koTjtZI42uizeWysWwM7SuwMAGZXG4kBwcl9pAwpBIrAks1DuSqqG\naRiGdXwS5O4bwYwWAVSxYYAQAlShPM3q9dPw3t91/wDhnuaJJOKjpZRV736tN3u97Xt3vpZa8beQ\nR5KjqC8RGXwqneFHGTjO4kbh82OGYgjAvbNJIy6Fx8qgAg73GGCKwbJ+bDYHcEscEBq6y8s83bCM\nsCEGYiCSDlwkqqT93OSVUbTIW3ErgVk3cEhBBKu4CHBxu8vDgnCszZHLFjhSpAI4YVJSaUGkvfun\ne1uVKSt17rrrdvdq5wOoWk4Q/PxsKvklCCCWbJRgMbOT8gCtublgNvL3cUywEkKOHxlpN4K7lZQz\nE9UIOBgq/wAzM8bK1d/fJNsLsFjKlywwWZhuYAMpQ7iUibARcEgDJJLHlbws0LgRCQ/OVDMI8ja5\nWPeyYjWTywHYB+CSowmWabW3/D6t9/P8uquSnZyfX1t9pPbqntbpdO7s78Hcn/RgGzlHBOUUGQGN\ngokfbgOrBdwJ8piExtNYE8MMsZ3ID0IkGeG3Zc/N5ilD8q7kCvg8AqHQ9s6pLaSJNEscxZVZVkjd\nVbYxb95kIxAXHyAdFVyylweblsbd1mYfu8Mm3YhXexYk52OCGx86tIv3QiBW6GoS1W3xLvbeafVv\nW2nRdnZNxLWE0na9mtLrSU33VtE/v6NXPnrwxYW8lrrz/ukUeMvFx5wsnz+J9VI2r5eQCiozEguQ\nr5DRhck1kIo5hHM3lblAOI2VxhHEjb48lpANzsRvYkAymVCTreGLOUW/icJN8zeMfFOxpY0dlQ+I\nr3BBkmIILAsrB2BYuCwkSQl8gmxL5vzOJI2lH7xmWQRgonD4Qoh8zLAyNvRoy6IM/SJKLqJbe0m9\n3f45bt637eTV7tO/kpzlF2f2ILqpXSik0k7xurN77xu21d8bLb3UYmJVNof5VRVBH7yUupEce7bg\nLtQ8KMKGZIzGvNyf6opIkiMBGTEUUPjgouYlBI4VgQSEVgCnlsWbtnAQ3DNFIApU7SIxs/fSo28G\n6copB+buGZgASHLYUixtHIDtU4GQURR8q5DBVkIBJXauNwDHPl7timr228vwvbXdf8F6vW7UFebs\nlZQje71SUrNqz319LSbbbTfFyxorsSCqEtvUsQCys20EKN+cMWUFShwwZhIcnFMW1ZI2Rm+RcFQ7\nEqVJLDCMFIUCTcVQEsdqsFZh2MlnDI5Z0581CgBTnDTZwhAGM7WVgpGWPzMHBrBuLK3ffsBQKjBO\n5RWRdqdflwMBCODk4UEs4LvXXf8Ar+uturWhPLZ20Vtmr2Sb1a76XtfS/V3lflZrFRuCNIGYY2KX\nAUbyBswcNtxgAYJRtwJZQKwzayJHKImfOxQjfNIAoBZSR5iFWVgytuIkYyYIZAc9Q9tJGHHmCQDO\nUCpl/mbJU7yQM7iSyksCow211OHLHMkUuNhICh1ReC21hlwIpCpIyrZcKRle+8IaVlNJ3T5LNt2S\n5lfq2vK7dnfRpNHN+XdCFshX5LfJGS6uxkAbAuFVXDAv5hfzAxYOokLSLkXDyJboZEIdWWTIwrqV\nQjnE4IKnymRFAYlgjyEiSuml3RK+Y3ZshgRySEcsApWE7soqyNubeqhCrKwKnIudrwEscKdiuplk\nXdjexCvsG13XdkBm+VwGG1WJiSTeuyT17dE999Xb56Xdwdrt6ata26rq+unR2v6sxp3QC0RNp3GL\ny2kji25e', '2016-06-17 09:28:15');

-- --------------------------------------------------------

--
-- Table structure for table `bdtestimonial`
--

CREATE TABLE `bdtestimonial` (
  `testimonial` text NOT NULL,
  `id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `email` text NOT NULL,
  `district` text NOT NULL,
  `state` text NOT NULL,
  `pic` longtext NOT NULL,
  `name` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bdtestimonial`
--

INSERT INTO `bdtestimonial` (`testimonial`, `id`, `created_at`, `email`, `district`, `state`, `pic`, `name`) VALUES
('xgg', 1, '2016-06-17 08:24:06', 'bobby@gmail.com', 'indore', 'mp', 'https://graph.facebook.com/10330263/picture?type=normal', '');

-- --------------------------------------------------------

--
-- Table structure for table `bdtwsignup`
--

CREATE TABLE `bdtwsignup` (
  `id` int(11) NOT NULL,
  `twname` text NOT NULL,
  `twid` varchar(20) NOT NULL,
  `twpic` text NOT NULL,
  `twemail` text NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `twstate` text NOT NULL,
  `twdistrict` text NOT NULL,
  `twmobile` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bdtwsignup`
--

INSERT INTO `bdtwsignup` (`id`, `twname`, `twid`, `twpic`, `twemail`, `created_at`, `twstate`, `twdistrict`, `twmobile`) VALUES
(5, 'bobby', '31065468', 'http://abs.twimg.com/sticky/default_profile_images/default_profile_5_normal.png', ' ', '2016-06-10 09:11:33', '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `bdvolunteerregister`
--

CREATE TABLE `bdvolunteerregister` (
  `IDno` int(11) NOT NULL,
  `gender` text NOT NULL,
  `age` text NOT NULL,
  `time` text NOT NULL,
  `volunteeringtype` text NOT NULL,
  `place` text NOT NULL,
  `referral` text NOT NULL,
  `areaofinterest` text NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `id` text NOT NULL,
  `type` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bdvolunteerregister`
--

INSERT INTO `bdvolunteerregister` (`IDno`, `gender`, `age`, `time`, `volunteeringtype`, `place`, `referral`, `areaofinterest`, `created_at`, `id`, `type`) VALUES
(1, 'Male', '22', 'Weekends', 'Home Based/ Online Work', 'Local', 'Playstore', 'Event Management', '2016-06-23 08:51:35', 'bobby', 'registered');

-- --------------------------------------------------------

--
-- Table structure for table `chat_rooms`
--

CREATE TABLE `chat_rooms` (
  `chat_room_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `chat_rooms`
--

INSERT INTO `chat_rooms` (`chat_room_id`, `name`, `created_at`) VALUES
(1, 'Lions Club Training Camp', '2016-06-24 21:17:10'),
(2, 'RedCross Society Blood Donation Event', '2016-06-24 21:17:10');

-- --------------------------------------------------------

--
-- Table structure for table `messages`
--

CREATE TABLE `messages` (
  `message_id` int(11) NOT NULL,
  `chat_room_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `message` text NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `messages`
--

INSERT INTO `messages` (`message_id`, `chat_room_id`, `user_id`, `message`, `created_at`) VALUES
(1, 1, 2, 'ghg', '2016-06-25 07:34:51'),
(2, 1, 2, 'hello', '2016-06-25 10:40:10'),
(3, 2, 2, 'gh', '2016-06-25 10:40:30'),
(4, 2, 2, 'gh', '2016-06-25 10:40:40'),
(5, 1, 2, 'hello', '2016-06-25 10:41:12'),
(6, 2, 2, 'ghf', '2016-06-25 10:44:07'),
(7, 2, 2, 'hello', '2016-06-25 11:22:41'),
(8, 1, 2, 'hello', '2016-06-25 12:41:57'),
(9, 1, 2, 'hello', '2016-06-25 14:05:47'),
(10, 1, 2, 'hello', '2016-06-25 14:08:44'),
(11, 1, 2, 'hello', '2016-06-25 17:37:37'),
(12, 1, 2, 'hello', '2016-06-25 17:38:35'),
(13, 1, 2, 'hello', '2016-06-25 17:39:14'),
(14, 1, 2, 'hello', '2016-06-25 17:44:55'),
(15, 1, 2, 'hello', '2016-06-25 17:46:44'),
(16, 2, 2, 'hello', '2016-06-25 17:47:11'),
(17, 2, 2, 'hello', '2016-06-25 17:50:52'),
(18, 2, 2, 'hello', '2016-06-25 17:57:25'),
(19, 2, 2, 'hello', '2016-06-25 18:01:06'),
(20, 2, 2, 'hello', '2016-06-25 18:11:57'),
(21, 2, 2, 'hello', '2016-06-25 18:12:41'),
(22, 2, 2, 'ghh hh', '2016-06-25 18:19:07'),
(23, 2, 2, 'bnn', '2016-06-25 18:19:28'),
(24, 2, 2, 'hello', '2016-06-25 18:43:40'),
(25, 2, 2, 'hello', '2016-06-25 18:46:29'),
(26, 2, 2, 'hello', '2016-06-25 19:07:50'),
(27, 2, 2, 'hello', '2016-06-25 19:09:01'),
(28, 2, 2, 'hello', '2016-06-25 19:10:13'),
(29, 2, 2, 'hello', '2016-06-25 19:11:29'),
(30, 2, 2, 'hello', '2016-06-25 19:15:33'),
(31, 2, 2, 'hello', '2016-06-25 19:16:18'),
(32, 2, 2, 'hello', '2016-06-25 19:17:56'),
(33, 2, 2, 'hello', '2016-06-25 19:19:30'),
(34, 2, 2, 'hello', '2016-06-25 19:23:19'),
(35, 1, 2, 'hello', '2016-06-25 19:24:00'),
(36, 2, 2, 'hi', '2016-06-25 19:36:24'),
(37, 1, 2, 'hhh', '2016-06-25 20:13:35'),
(38, 1, 2, 'gh', '2016-06-25 20:25:07'),
(39, 2, 2, 'hello', '2016-06-25 20:40:56'),
(40, 2, 2, 'hello', '2016-06-25 20:41:29'),
(41, 1, 2, 'qfg', '2016-06-25 20:56:40'),
(42, 2, 2, 'hi', '2016-06-25 22:30:57'),
(43, 1, 2, 'gh', '2016-06-25 23:14:33'),
(44, 1, 2, 'ghjgg', '2016-06-25 23:14:48'),
(45, 1, 4, 'hjb', '2016-06-25 23:16:31'),
(46, 1, 4, 'fggdh', '2016-06-25 23:17:48'),
(47, 1, 2, 'fhh', '2016-06-25 23:34:47'),
(48, 1, 2, 'blood donation camp will be organised on 27 june near mog lines,indore. If anyone is interested in volunteering for the same, please contact us.\nThank YouðŸ™ðŸ˜€', '2016-06-25 23:55:25');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `gcm_registration_id` text NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `name`, `email`, `gcm_registration_id`, `created_at`) VALUES
(2, 'bobby', 'bobby@gmail.com', 'cxSe3GQw0vc:APA91bHZO7JKxRKcJIgw7S7Ky7tAgmaXyAsiBH56UgQijqlxne2rma4da95tFfnn3TjO2Oq_RpB3TRQnhV3EARGG-0G2AufLqhr9s4siqbaohCJhWpCxmtrvKS8Pt3g1PR4', '2016-06-24 20:50:39'),
(3, 'bj', 'bobby1@gmail.com', 'dbcXO0X4hlQ:APA91bETxv6JmvyKUm-vAF3LemlpzXfVmt0RMrgqRP2wuWrx93_ZG36p6UU7TPWb-ZSzL8azXUqVVn9Rw0yil2GczsKSQN1JDSzzw7-HUlSF7KWg32xV7C4tPSOhcw2OsD62NU', '2016-06-24 21:22:39'),
(4, 'bj123', 'bj@gmail.com', 'cxSe3GQw0vc:APA91bHZO7JKxRKcJZQS7Ky7tAgmaXyAsiBH56UgQijqlxne2rma4da95tFfnn3TjO2Oq_RpB3TRQnhV3EARGG-0G2AufLqhr9s4siqbaohCJhWpCxmtrvKS8Pt3g1PR4', '2016-06-25 23:15:55');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bdblog`
--
ALTER TABLE `bdblog`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `bddonorregister`
--
ALTER TABLE `bddonorregister`
  ADD PRIMARY KEY (`IDno`);

--
-- Indexes for table `bdevents`
--
ALTER TABLE `bdevents`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `bdfbsignup`
--
ALTER TABLE `bdfbsignup`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `fbid` (`fbid`);

--
-- Indexes for table `bdglsignup`
--
ALTER TABLE `bdglsignup`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `glid` (`glid`);

--
-- Indexes for table `bdsignup`
--
ALTER TABLE `bdsignup`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `bdtestimonial`
--
ALTER TABLE `bdtestimonial`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bdtwsignup`
--
ALTER TABLE `bdtwsignup`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `twid` (`twid`);

--
-- Indexes for table `bdvolunteerregister`
--
ALTER TABLE `bdvolunteerregister`
  ADD PRIMARY KEY (`IDno`);

--
-- Indexes for table `chat_rooms`
--
ALTER TABLE `chat_rooms`
  ADD PRIMARY KEY (`chat_room_id`);

--
-- Indexes for table `messages`
--
ALTER TABLE `messages`
  ADD PRIMARY KEY (`message_id`),
  ADD KEY `chat_room_id` (`chat_room_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `chat_room_id_2` (`chat_room_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bdblog`
--
ALTER TABLE `bdblog`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `bddonorregister`
--
ALTER TABLE `bddonorregister`
  MODIFY `IDno` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `bdevents`
--
ALTER TABLE `bdevents`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `bdfbsignup`
--
ALTER TABLE `bdfbsignup`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `bdglsignup`
--
ALTER TABLE `bdglsignup`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `bdsignup`
--
ALTER TABLE `bdsignup`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `bdtestimonial`
--
ALTER TABLE `bdtestimonial`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `bdtwsignup`
--
ALTER TABLE `bdtwsignup`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `bdvolunteerregister`
--
ALTER TABLE `bdvolunteerregister`
  MODIFY `IDno` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `messages`
--
ALTER TABLE `messages`
  MODIFY `message_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
