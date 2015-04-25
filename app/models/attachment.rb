# Model Attachment
# Table attachments
class Attachment < ActiveRecord::Base
  has_attached_file :file

  belongs_to :activity

  validates :file, attachment_presence: true
  validates :name, presence: true
  do_not_validate_attachment_file_type :file
end
