package chbbo.BEOhGam.service;

import chbbo.BEOhGam.domain.Member;
import chbbo.BEOhGam.domain.Note;
import chbbo.BEOhGam.domain.Text;
import chbbo.BEOhGam.dto.MemberDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class NoteServiceTest {

    @Autowired
    NoteService noteService;
    @Autowired
    MemberService memberService;
    static Long i = 100L;
    static Long j = 300L;
    static Long k = 500L;

    @Test
    @Transactional
    void findAllByUploadAtTest() {
        Member member = memberService.findByUserId("test");

        Note note1 = new Note();
        Note note2 = new Note();
        Set<Long> likeMember = new HashSet<>();
        likeMember.add(member.getId());
        note1.setLikeMember(likeMember);
        note2.setLikeMember(likeMember);

        Text text1 = new Text();
        text1.setContent("hi");
        List<Text> textList1 = new ArrayList<>();
        textList1.add(text1);
        note1.setText(textList1);

        Text text2 = new Text();
        text2.setContent("hi");
        List<Text> textList2 = new ArrayList<>();
        textList2.add(text2);
        note2.setText(textList2);

        note1.setMember(member);
        note2.setMember(member);
        noteService.save(note1);
        noteService.save(note2);

        List<Note> foundNote = noteService.findAllByUploadAt(LocalDateTime.now().toLocalDate().atStartOfDay(),
                LocalDateTime.now().toLocalDate().atTime(LocalTime.MAX));
//        List<Note> foundNote = noteService.findAllByUploadAt(LocalDateTime.of(2023, 7, 4, 0, 0), LocalDateTime.of(2023, 7, 5, 0, 0));
        assertThat(note1).isIn(foundNote);
        assertThat(note2).isIn(foundNote);
    }

    @Test
    @Transactional
    void findAllByUserIdAndUploadAtTest() {
        // given
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setUserId("testtest" + i);
        i++;
        memberDTO.setPassword("test1234");
        memberDTO.setNickname("testtest");
        memberDTO.setUsername("테스트");
        memberDTO.setPhone("010-0000-0000");


        Note note3 = new Note();


        Text text3 = new Text();
        text3.setContent("hi1");
        List<Text> textList3 = new ArrayList<>();
        textList3.add(text3);
        note3.setText(textList3);

        // when
        memberService.join(memberDTO);
        Member member = memberService.findByUserId(memberDTO.getUserId());
        Set<Long> likeMember = new HashSet<>();
        likeMember.add(member.getId());
        note3.setLikeMember(likeMember);
        note3.setMember(member);
        noteService.save(note3);

        List<Note> notes = noteService.findAllByUserIdAndUploadAt(memberDTO.getUserId(),
                LocalDateTime.now().toLocalDate().atStartOfDay(),
                LocalDateTime.now().toLocalDate().atTime(LocalTime.MAX));
        System.out.println(notes);

        // then
        assertThat(note3).isIn(notes);
        System.out.println(notes);
    }

    @Test
    @Transactional
    void findAllByUserIdTest() {
        // given
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setUserId("testtesttest" + j);
        j++;
        memberDTO.setPassword("test1234");
        memberDTO.setNickname("testtest");
        memberDTO.setUsername("테스트");
        memberDTO.setPhone("010-0000-0000");

        Note note1 = new Note();

        Text text1 = new Text();
        text1.setContent("hi1");
        List<Text> textList1 = new ArrayList<>();
        textList1.add(text1);
        note1.setText(textList1);

        Note note2 = new Note();

        Text text2 = new Text();
        text2.setContent("hi2");
        List<Text> textList2 = new ArrayList<>();
        textList2.add(text2);
        note2.setText(textList2);

        Note note3 = new Note();

        Text text3 = new Text();
        text3.setContent("hi3");
        List<Text> textList3 = new ArrayList<>();
        textList3.add(text3);
        note3.setText(textList3);

        // when
        memberService.join(memberDTO);
        Member member = memberService.findByUserId(memberDTO.getUserId());
        Set<Long> likeMember = new HashSet<>();
        likeMember.add(member.getId());
        note1.setLikeMember(likeMember);
        note2.setLikeMember(likeMember);
        note3.setLikeMember(likeMember);
        note1.setMember(member);
        note2.setMember(member);
        note3.setMember(member);
        noteService.save(note1);
        noteService.save(note2);
        noteService.save(note3);

        List<Note> foundNotes = noteService.findAllByUserId(memberDTO.getUserId());
        System.out.println(foundNotes);

        // then
        assertThat(foundNotes.size()).isSameAs(3);
        assertThat(note1).isIn(foundNotes);
        assertThat(note2).isIn(foundNotes);
        assertThat(note3).isIn(foundNotes);
    }

    @Test
    @Transactional
    void deleteNoteTest() {
        // given
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setUserId("testtesttesttest" + k);
        k++;
        memberDTO.setPassword("test1234");
        memberDTO.setNickname("testtest");
        memberDTO.setUsername("테스트");
        memberDTO.setPhone("010-0000-0000");

        Note note1 = new Note();

        Text text1 = new Text();
        text1.setContent("hi1");
        List<Text> textList1 = new ArrayList<>();
        textList1.add(text1);
        note1.setText(textList1);

        // when
        memberService.join(memberDTO);
        Member member = memberService.findByUserId(memberDTO.getUserId());
        note1.setMember(member);
        noteService.save(note1);

        noteService.deleteNote(memberDTO.getUserId(), LocalDateTime.now().toLocalDate().atStartOfDay(),
                LocalDateTime.now().toLocalDate().atTime(LocalTime.MAX));

        List<Note> notes = noteService.findAllByUserIdAndUploadAt(memberDTO.getUserId(),
                LocalDateTime.now().toLocalDate().atStartOfDay(),
                LocalDateTime.now().toLocalDate().atTime(LocalTime.MAX));
//        // then
        assertThat(note1).isNotIn(notes);
    }

    @Test
    @Transactional
    void addAndRemoveLikeMemberTest() {
        // given
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setUserId("테스트" + k);
        k++;
        memberDTO.setPassword("test1234");
        memberDTO.setNickname("testtest");
        memberDTO.setUsername("테스트");
        memberDTO.setPhone("010-0000-0000");
        memberService.join(memberDTO);
        Member member = memberService.findByUserId(memberDTO.getUserId());

        noteService.addLikeMemberToNote(memberDTO.getUserId(), "test",
                LocalDate.of(2023, 7, 7).atStartOfDay(),
                LocalDate.of(2023, 7, 7).atTime(LocalTime.MAX));
        Note addNote = noteService.findAllByUserIdAndUploadAt("test",
                LocalDate.of(2023, 7, 7).atStartOfDay(),
                LocalDate.of(2023, 7, 7).atTime(LocalTime.MAX)).get(0);
        Set<Long> addLikeMember = addNote.getLikeMember();
        System.out.println(addLikeMember);
        assertThat(member.getId()).isIn(addLikeMember);

        noteService.removeLikeMemberFromNote(memberDTO.getUserId(), "test",
                LocalDate.of(2023, 7, 7).atStartOfDay(),
                LocalDate.of(2023, 7, 7).atTime(LocalTime.MAX));
        Note removeNote = noteService.findAllByUserIdAndUploadAt("test",
                LocalDate.of(2023, 7, 7).atStartOfDay(),
                LocalDate.of(2023, 7, 7).atTime(LocalTime.MAX)).get(0);
        Set<Long> removeLikeMember = removeNote.getLikeMember();
        System.out.println(removeLikeMember);
        assertThat(member.getId()).isNotIn(removeLikeMember);
    }

//    @Test
//    void test() {
//        // given
//        MemberDTO memberDTO = new MemberDTO();
//        memberDTO.setUserId("test");
//        memberDTO.setPassword("test1234");
//        memberDTO.setNickname("testtest");
//        memberDTO.setUsername("테스트");
//        memberDTO.setPhone("010-0000-0000");
//        memberService.join(memberDTO);
//        Member member = memberService.findByUserId("test");
//
//        Note note1 = new Note();
//
//        Text text1 = new Text();
//        text1.setContent("test1");
//        Text text2 = new Text();
//        text2.setContent("test2");
//        Text text3 = new Text();
//        text3.setContent("test3");
//        List<Text> textList1 = new ArrayList<>();
//        textList1.add(text1);
//        textList1.add(text2);
//        textList1.add(text3);
//        note1.setText(textList1);
//        note1.setIsPublic(false);
//        note1.setMember(member);
//
//        noteService.save(note1);
//    }
}
