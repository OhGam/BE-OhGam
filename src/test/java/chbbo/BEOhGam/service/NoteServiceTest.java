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
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class NoteServiceTest {

    @Autowired
    NoteService noteService;
    @Autowired
    MemberService memberService;
    static Long i = 1L;

    @Test
    @Transactional
    void saveAndFindNoteTest() {
        // given
        Note note = new Note();
        Text text1 = new Text();
        text1.setContent("hi");
        Text text2 = new Text();
        text2.setContent("hello");
        Text text3 = new Text();
        text3.setContent("ntmu");

        // when
        List<Text> text = new ArrayList<>();
        text.add(text1);
        text.add(text2);
        text.add(text3);
        note.setText(text);

        noteService.save(note);
        Note foundNote = noteService.findNote(note.getId());

        // then
        assertThat(foundNote).isSameAs(note);
        assertThat(foundNote.getText()).isSameAs(note.getText());
    }

    @Test
    @Transactional
    void findAllByUploadAtTest() {
        Note note1 = new Note();
        Note note2 = new Note();

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

        noteService.save(note1);
        noteService.save(note2);

        List<Note> foundNote = noteService.findAllByUploadAt(LocalDateTime.now().toLocalDate().atStartOfDay(), LocalDateTime.now().toLocalDate().atTime(LocalTime.MAX));
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
        memberDTO.setUserId("testtest" + i);
        i++;
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
}
